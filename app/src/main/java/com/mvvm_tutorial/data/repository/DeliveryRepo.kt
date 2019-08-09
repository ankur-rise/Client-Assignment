package com.mvvm_tutorial.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm_tutorial.data.IDeliveryRepo
import com.mvvm_tutorial.data.cache.UserCache
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.data.models.UserDeliveryDataModel
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
    private val userCache: UserCache
) : IDeliveryRepo {
    val erroData: MutableLiveData<String> = MutableLiveData()

    override fun getDeliveryItems(offset: Int, limit: Int): LiveData<List<DeliveryItemDataModel>> {
        // This isn't an optimal implementation. We'll fix it later.
        /*val cacheData = userCache.get(userId)
        if(cacheData!=null) {
            return cacheData
        }*/
        val data = MutableLiveData<List<DeliveryItemDataModel>>()
//        userCache.set(userId, data)
        val map = HashMap<String, Int>()
        map["offset"] = offset
        map["limit"] = limit
        val call = api.getDeliveries(map)
        call.enqueue(object : Callback<UserDeliveryDataModel> {
            override fun onResponse(call: Call<UserDeliveryDataModel>, response: Response<UserDeliveryDataModel>) {
                val dataDelivery = response.body()
                if (dataDelivery != null)
                    data.value = dataDelivery.list
                else
                    erroData.value = "Not abe to fetch latest data"
            }

            override fun onFailure(call: Call<UserDeliveryDataModel>, t: Throwable) {
                erroData.value = "Not abe to fetch latest data"
            }
        })

        return data
    }
}

