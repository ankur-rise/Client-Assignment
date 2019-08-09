package com.mvvm_tutorial.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import com.mvvm_tutorial.data.IDeliveryRepo
import com.mvvm_tutorial.data.db.DeliveryDao
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.data.network.Apis
import org.jetbrains.annotations.NotNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeliveryRepo @Inject constructor(
    @NotNull private val api: Apis,
    private val dao:DeliveryDao
) : IDeliveryRepo {
    val erroData: MutableLiveData<String> = MutableLiveData()

    override fun getDeliveryItems(offset: Int, limit: Int): LiveData<List<DeliveryItemDataModel>> {

        val data = dao.get(SimpleSQLiteQuery("Select * from DeliveryItemDataModel"))

        fetchDataFromServer(offset, limit)

        return data
    }

    private fun fetchDataFromServer(offset: Int, limit: Int) {
        val map = HashMap<String, Int>()
        map["offset"] = offset
        map["limit"] = limit
        val call = api.getDeliveries(map)
        call.enqueue(object : Callback<List<DeliveryItemDataModel>> {
            override fun onResponse(call: Call<List<DeliveryItemDataModel>>, response: Response<List<DeliveryItemDataModel>>) {
                val dataDelivery = response.body()
                if (dataDelivery != null)
                Thread {
                    dao.insertAll(dataDelivery)
                }.start()
                else
                    erroData.value = "Not abe to fetch latest data"

            }

            override fun onFailure(call: Call<List<DeliveryItemDataModel>>, t: Throwable) {
                erroData.value = "Not abe to fetch latest data"
            }
        })
    }
}

