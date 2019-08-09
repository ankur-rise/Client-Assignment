package com.mvvm_tutorial.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.data.repository.DeliveryRepo
import com.mvvm_tutorial.ui.models.User
import java.lang.IllegalArgumentException
import javax.inject.Inject

class UserProfileViewModel @Inject
constructor(savedStateHandle: SavedStateHandle, deliveryRepo: DeliveryRepo) : ViewModel() {
    var userId:String = savedStateHandle.get("uid")?: throw IllegalArgumentException("Missing deliveryItems id")
    var deliveryItems: LiveData<List<DeliveryItemDataModel>>? = deliveryRepo.getDeliveryItems(0, 20)
}