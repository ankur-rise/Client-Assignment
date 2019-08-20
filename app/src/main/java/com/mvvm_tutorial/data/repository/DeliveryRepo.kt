package com.mvvm_tutorial.data.repository

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mvvm_tutorial.data.IDeliveryRepo
import com.mvvm_tutorial.data.RepoBoundaryCallback
import com.mvvm_tutorial.data.RepoBoundaryCallback.Companion.NETWORK_PAGE_SIZE
import com.mvvm_tutorial.data.db.DeliveryDao
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.data.models.RepoResult
import com.mvvm_tutorial.data.network.Apis
import org.jetbrains.annotations.NotNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.thread

@Singleton
class DeliveryRepo @Inject constructor(
    @NotNull private val api: Apis,
    private val dao: DeliveryDao
) : IDeliveryRepo {

    override fun getDeliveryItems(): RepoResult {
        val dataSourceFactory = dao.get()
        val callback = RepoBoundaryCallback(api, dao)
        val networkErr = callback.networkErrors

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(DATABASE_PAGE_SIZE)
            .build()

        val pagedList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).setBoundaryCallback(callback).build()
        return RepoResult(pagedList, networkErr)
    }


    override fun refreshDeliveryItems(onError: (errMsg: String) -> Unit) {
        val map = HashMap<String, Int>()
        map["offset"] = 0
        map["limit"] = NETWORK_PAGE_SIZE
        val call = api.getDeliveries(map)
        call.enqueue(object : Callback<List<DeliveryItemDataModel>> {
            override fun onResponse(
                call: Call<List<DeliveryItemDataModel>>,
                response: Response<List<DeliveryItemDataModel>>
            ) {
                val dataDelivery: List<DeliveryItemDataModel>? = response.body()
                if (dataDelivery != null && dataDelivery.isNotEmpty())
                    thread {

                        dao.clearTable()
                        dao.insertAll(dataDelivery)

                    }
                else {
                    onError("Server error!")
                }

            }

            override fun onFailure(call: Call<List<DeliveryItemDataModel>>, t: Throwable) {
                onError(t.message ?: "Network error")
            }
        })
    }


    companion object {
        private const val DATABASE_PAGE_SIZE = 20
        const val INITIAL_LOAD_SIZE_HINT = 20
    }
}

