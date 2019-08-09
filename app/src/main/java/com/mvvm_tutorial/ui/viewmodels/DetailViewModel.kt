package com.mvvm_tutorial.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.data.repository.DeliveryRepo
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val deliveryRepo: DeliveryRepo) : ViewModel() {
    var deliveryItems: LiveData<List<DeliveryItemDataModel>>? = MutableLiveData()
    fun loadUser() {
        Log.i(DetailViewModel::class.java.simpleName, "repo instance created")
        deliveryRepo.getDeliveryItems(0, 20)

    }

}

