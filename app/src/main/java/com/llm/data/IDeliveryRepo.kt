package com.llm.data

import com.llm.data.models.RepoResult

interface IDeliveryRepo {

    fun getDeliveryItems(): RepoResult

    fun refreshDeliveryItems(onError:(errMsg:String)-> Unit)
}