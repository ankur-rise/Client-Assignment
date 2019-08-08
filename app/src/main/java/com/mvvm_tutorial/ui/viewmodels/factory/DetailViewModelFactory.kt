package com.mvvm_tutorial.ui.viewmodels.factory

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.mvvm_tutorial.data.repository.UserRepo
import com.mvvm_tutorial.ui.viewmodels.UserDetailViewModel

class DetailViewModelFactory(val repo:UserRepo, owner:SavedStateRegistryOwner, bundle: Bundle) :
    AbstractSavedStateViewModelFactory(owner, bundle) {
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return UserDetailViewModel(repo, handle) as T
    }
}