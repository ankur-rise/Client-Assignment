package com.mvvm_tutorial.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import com.mvvm_tutorial.data.IDeliveryRepo
import com.mvvm_tutorial.data.db.DeliveryDao
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.data.network.Apis
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.annotations.NotNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeliveryRepo @Inject constructor(
    @NotNull private val api: Apis,
    private val dao: DeliveryDao
) : IDeliveryRepo {
    val errorData: MutableLiveData<String> = MutableLiveData()


    override fun getDeliveryItems(offset: Int, limit: Int): LiveData<PagedList<DeliveryItemDataModel>> {

        val dataSource = dao.get(SimpleSQLiteQuery("Select * from DeliveryItemDataModel"))
        val data = LivePagedListBuilder(dataSource, 10).build()
        return data
    }

    public fun fetchDataFromServer(offset: Int, limit: Int) {
        val map = HashMap<String, Int>()
        map["offset"] = offset
        map["limit"] = limit
        val call = api.getDeliveries(map)
        call.enqueue(object : Callback<List<DeliveryItemDataModel>> {
            override fun onResponse(
                call: Call<List<DeliveryItemDataModel>>,
                response: Response<List<DeliveryItemDataModel>>
            ) {
                val dataDelivery = response.body()
                if (dataDelivery != null)
                    runBlocking {
                        launch {
                            dataBaseSave(dataDelivery)
                        }
                    }
                else
                    errorData.value = "Not abe to fetch latest data"

            }

            override fun onFailure(call: Call<List<DeliveryItemDataModel>>, t: Throwable) {
                errorData.value = "Not abe to fetch latest data"
            }
        })
    }

    suspend fun dataBaseSave(list: List<DeliveryItemDataModel>) {
        dao.insertAll(list)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}

