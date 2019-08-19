package com.mvvm_tutorial.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.mvvm_tutorial.data.db.DeliveryDao
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.data.network.Apis
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class RepoBoundaryCallback(private val api : Apis, private val dao: DeliveryDao) : PagedList.BoundaryCallback<DeliveryItemDataModel>() {
    private val TAG:String = RepoBoundaryCallback::class.java.simpleName
    private var isRequestInProgress:Boolean = false

    // keep the last requested page.
    // When the request is successful, increment the page number.
    private var lastRequestedPage = 0

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors


    override fun onZeroItemsLoaded() {
        Log.i(TAG, "onZeroItemsLoaded")
        fetchDataFromServer(lastRequestedPage*NETWORK_PAGE_SIZE, NETWORK_PAGE_SIZE)

    }

    override fun onItemAtEndLoaded(itemAtEnd: DeliveryItemDataModel) {
        Log.i(TAG, "onItemAtEndLoaded")
        fetchDataFromServer(itemAtEnd.id+1, NETWORK_PAGE_SIZE)
    }

    private fun fetchDataFromServer(offset: Int, limit: Int) {
        if( isRequestInProgress) return

        isRequestInProgress = true
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
                    thread {
                        dao.insertAll(dataDelivery)
                        lastRequestedPage++
                        isRequestInProgress=false

                    }
                else {
                    _networkErrors.value = "Not abe to fetch latest data"
                }

            }

            override fun onFailure(call: Call<List<DeliveryItemDataModel>>, t: Throwable) {
                _networkErrors.value = "Not abe to fetch latest data"
            }
        })
    }




    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

}