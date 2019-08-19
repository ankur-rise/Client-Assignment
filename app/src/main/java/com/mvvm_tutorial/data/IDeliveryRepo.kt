package com.mvvm_tutorial.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.data.models.RepoResult

interface IDeliveryRepo {

    fun getDeliveryItems(): RepoResult

}