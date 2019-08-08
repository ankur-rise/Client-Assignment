package com.mvvm_tutorial.ui.viewmodels.factory

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.mvvm_tutorial.di.factory.ViewModelAssistedFactory
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val creators: MutableMap<Class<out ViewModel>,
        ViewModelAssistedFactory<out ViewModel>>, owner:SavedStateRegistryOwner, defaultArg: Bundle?)
    : AbstractSavedStateViewModelFactory(owner, defaultArg) {
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        val factory = creators[modelClass]
        return factory?.create(handle) as T ?: throw IllegalArgumentException("No such view model found!")
    }


}