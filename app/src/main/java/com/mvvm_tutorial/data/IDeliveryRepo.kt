package com.mvvm_tutorial.data

import com.mvvm_tutorial.data.models.RepoResult

interface IDeliveryRepo {

    fun getDeliveryItems(): RepoResult

    fun refreshDeliveryItems(onError:(errMsg:String)-> Unit)
}