package com.llm.data.repository

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.llm.data.CustomBoundaryCallback
import com.llm.data.IDeliveryRepo
import com.llm.data.db.DeliveryDao
import com.llm.data.models.DeliveryItemDataModel
import com.llm.data.models.RepoResult
import com.llm.data.network.Apis
import com.llm.data.network.CallbackWithRetry
import com.llm.di.qualifiers.SingleThreadExecutor
import com.llm.ui.utils.NETWORK_ERR_MESSAGE
import com.llm.ui.utils.NetworkUtils
import org.jetbrains.annotations.NotNull
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeliveryRepo @Inject constructor(
    @NotNull private val api: Apis,
    private val dao: DeliveryDao,
    private val networkUtils: NetworkUtils,
    @SingleThreadExecutor private val executor: Executor
) : IDeliveryRepo {

    override fun getDeliveryItems(): RepoResult {
        val dataSourceFactory = dao.get()
        val callback = CustomBoundaryCallback(dao, executor, :: getDeliveries)
        val networkErr = callback.networkErrors

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(DATABASE_PAGE_SIZE)
            .build()

        val pagedList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).setBoundaryCallback(callback).build()
        return RepoResult(pagedList, networkErr)
    }


    override fun refreshData( onError: (errMsg: String) -> Unit){
        getDeliveries(
            0,
            NETWORK_PAGE_SIZE,
            onSuccess = { data: List<DeliveryItemDataModel> -> refreshDB(data) },
            onError = {
                onError(it)
            })
    }

    override fun getDeliveries(
        offset: Int,
        limit: Int,
        onSuccess: (data: List<DeliveryItemDataModel>) -> Unit,
        onError: (errMsg: String) -> Unit
    ) {
        if (!networkUtils.isConnectedToInternet()) {
            onError(NETWORK_ERR_MESSAGE)
            return
        }
        val map = HashMap<String, Int>()
        map["offset"] = offset
        map["limit"] = limit
        val call = api.getDeliveries(map)
        call.enqueue(object : CallbackWithRetry<List<DeliveryItemDataModel>>() {
            override fun onResponse(
                call: Call<List<DeliveryItemDataModel>>,
                response: Response<List<DeliveryItemDataModel>>
            ) {
                if (response.isSuccessful) {
                    val dataDelivery: List<DeliveryItemDataModel>? = response.body()
                    if (dataDelivery != null && dataDelivery.isNotEmpty())
                        onSuccess(dataDelivery)
                    else {
                        onError("You have reached to the end of list.")
                    }
                } else {
                    onError(response.message())
                }

            }

            override fun onFailure(call: Call<List<DeliveryItemDataModel>>, t: Throwable) {

                super.onFailure(call, t)
                if (!isRetry()) {
                    onError(t.message ?: "Network error")
                }
            }
        })
    }

    private fun refreshDB(data: List<DeliveryItemDataModel>) {
        executor.execute {
            dao.clearTable()
            dao.insertAll(data)
        }
    }


    companion object {
        private const val DATABASE_PAGE_SIZE = 20
        const val INITIAL_LOAD_SIZE_HINT = 20
        const val NETWORK_PAGE_SIZE = 20
    }
}

