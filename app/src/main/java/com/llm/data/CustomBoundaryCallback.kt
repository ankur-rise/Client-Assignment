package com.llm.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.llm.data.db.DeliveryDao
import com.llm.data.models.DeliveryItemDataModel
import com.llm.data.network.Apis
import com.llm.data.network.CallbackWithRetry
import retrofit2.Call
import retrofit2.Response
import kotlin.concurrent.thread

class CustomBoundaryCallback(private val api : Apis, private val dao: DeliveryDao) : PagedList.BoundaryCallback<DeliveryItemDataModel>() {
    private val TAG:String = CustomBoundaryCallback::class.java.simpleName
    private var isRequestInProgress:Boolean = false


    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors


    override fun onZeroItemsLoaded() {
        Log.i(TAG, "onZeroItemsLoaded")
        fetchDataFromServer(0, NETWORK_PAGE_SIZE)

    }

    override fun onItemAtEndLoaded(itemAtEnd: DeliveryItemDataModel) {
        Log.i(TAG, "onItemAtEndLoaded")
        fetchDataFromServer(itemAtEnd.id+1, NETWORK_PAGE_SIZE)
    }

    private fun fetchDataFromServer(offset: Int, limit: Int) {
        if(isRequestInProgress) return

        isRequestInProgress = true
        val map = HashMap<String, Int>()
        map["offset"] = offset
        map["limit"] = limit
        val call = api.getDeliveries(map)
        call.enqueue(object : CallbackWithRetry<List<DeliveryItemDataModel>>() {
            override fun onResponse(
                call: Call<List<DeliveryItemDataModel>>,
                response: Response<List<DeliveryItemDataModel>>
            ) {
                val dataDelivery:List<DeliveryItemDataModel>? = response.body()
                if (dataDelivery != null && dataDelivery.isNotEmpty()) {
                    thread {
                        dao.insertAll(dataDelivery)
                        isRequestInProgress = false
                    }

                }
                else {
                    isRequestInProgress = false
                    _networkErrors.value = "You have reached to the end of list."
                }

            }

            override fun onFailure(call: Call<List<DeliveryItemDataModel>>, t: Throwable) {
                super.onFailure(call, t)
                if(!isRetry()) {
                    _networkErrors.value = "Not able to fetch latest data"
                    isRequestInProgress = false
                }
            }
        })
    }




    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

}