package com.llm.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.llm.data.models.DeliveryItemDataModel
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {

    private val _dataModel: MutableLiveData<DeliveryItemDataModel> = MutableLiveData()
    val description: LiveData<String> = Transformations.map(_dataModel) { it.description }
    val imageUrl: LiveData<String> = Transformations.map(_dataModel) { it.imageUrl }

    fun loadData(dataModel: DeliveryItemDataModel) {
        _dataModel.postValue(dataModel)
    }


}