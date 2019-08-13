package com.mvvm_tutorial.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.data.repository.DeliveryRepo
import javax.inject.Inject

class DeliveryItemsViewModel @Inject constructor(private val deliveryRepo: DeliveryRepo) : ViewModel() {
private var currentIndex = 0
    fun loadUser() : LiveData<PagedList<DeliveryItemDataModel>>{
        return deliveryRepo.getDeliveryItems(0, 20)
    }

    fun loadMore(){
        deliveryRepo.fetchDataFromServer(20, 40)
    }

}

