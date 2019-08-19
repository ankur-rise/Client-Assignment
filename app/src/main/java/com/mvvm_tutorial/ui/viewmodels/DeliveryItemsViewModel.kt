package com.mvvm_tutorial.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.data.models.RepoResult
import com.mvvm_tutorial.data.repository.DeliveryRepo
import javax.inject.Inject

class DeliveryItemsViewModel @Inject constructor(private val deliveryRepo: DeliveryRepo) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<RepoResult> = Transformations.map(queryLiveData) {
        deliveryRepo.getDeliveryItems()
    }


    val resultLiveData:LiveData<PagedList<DeliveryItemDataModel>> = Transformations.switchMap(repoResult){it.repoResult}
    val errLiveData:LiveData<String> = Transformations.switchMap(repoResult){it.networkErr}
    fun loadUser() {
        queryLiveData.postValue("")
    }


}

