package com.mvvm_tutorial.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.data.repository.DeliveryRepo
import javax.inject.Inject

class DeliveryItemsViewModel @Inject constructor(private val deliveryRepo: DeliveryRepo) : ViewModel() {

    fun loadUser() : LiveData<List<DeliveryItemDataModel>>{

        return deliveryRepo.getDeliveryItems(0, 20)

    }

}

