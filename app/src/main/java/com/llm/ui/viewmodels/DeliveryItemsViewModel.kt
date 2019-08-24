package com.llm.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.llm.data.models.DeliveryItemDataModel
import com.llm.data.models.RepoResult
import com.llm.data.repository.DeliveryRepo
import com.llm.data.repository.DeliveryRepo.Companion.NETWORK_PAGE_SIZE
import javax.inject.Inject

class DeliveryItemsViewModel @Inject constructor(private val deliveryRepo: DeliveryRepo) : ViewModel() {

    private val deliveryRepoResult = MutableLiveData<RepoResult>()
    private val repoResult: LiveData<RepoResult> = deliveryRepoResult


    val resultLiveData:LiveData<PagedList<DeliveryItemDataModel>> = Transformations.switchMap(repoResult){it.deliveryData}
    val errLiveData:LiveData<String> = Transformations.switchMap(repoResult){it.networkErr}
    fun loadUser() {
//        deliveryRepoResult.postValue(Unit)
        deliveryRepoResult.postValue(deliveryRepo.getDeliveryItems())
    }


    private var _errRefresh = MutableLiveData<String>()
     val errRefreshLiveData:LiveData<String> = Transformations.map(_errRefresh){
         it
     }
    fun refreshData() {
        deliveryRepo.refreshData(onError = {

            _errRefresh.postValue(it)

        })
    }


}

