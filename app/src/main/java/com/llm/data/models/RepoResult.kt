package com.llm.data.models

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class RepoResult(val deliveryData:LiveData<PagedList<DeliveryItemDataModel>>, val networkErr:LiveData<String>)