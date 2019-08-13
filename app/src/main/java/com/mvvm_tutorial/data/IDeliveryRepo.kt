package com.mvvm_tutorial.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mvvm_tutorial.data.models.DeliveryItemDataModel

interface IDeliveryRepo {

    fun getDeliveryItems(offset:Int, limit:Int): LiveData<PagedList<DeliveryItemDataModel>>

}