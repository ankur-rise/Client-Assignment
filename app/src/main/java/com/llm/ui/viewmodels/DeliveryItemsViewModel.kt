package com.llm.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.llm.data.models.DeliveryItemDataModel
import com.llm.data.models.RepoResult
import com.llm.data.repository.DeliveryRepo
import javax.inject.Inject

class DeliveryItemsViewModel @Inject constructor(private val deliveryRepo: DeliveryRepo) : ViewModel() {

    private val queryLiveData = MutableLiveData<Unit>()
    private val repoResult: LiveData<RepoResult> = Transformations.map(queryLiveData) {
        deliveryRepo.getDeliveryItems()
    }


    val resultLiveData:LiveData<PagedList<DeliveryItemDataModel>> = Transformations.switchMap(repoResult){it.repoResult}
    val errLiveData:LiveData<String> = Transformations.switchMap(repoResult){it.networkErr}
    fun loadUser() {
        queryLiveData.postValue(Unit)
    }

    val errRefreshLiveData:MutableLiveData<String> = MutableLiveData()
    fun refreshData() {
           deliveryRepo.refreshDeliveryItems {
                errRefreshLiveData.value = it
           }


    }


}

