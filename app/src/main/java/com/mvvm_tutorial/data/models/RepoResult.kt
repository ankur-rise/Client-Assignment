package com.mvvm_tutorial.data.models

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class RepoResult(val repoResult:LiveData<PagedList<DeliveryItemDataModel>>, val networkErr:LiveData<String>)