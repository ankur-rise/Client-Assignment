package com.mvvm_tutorial.di.factory

import androidx.lifecycle.ViewModel

interface ViewModelAssistedFactory<T: ViewModel> {

        fun create(savedState: androidx.lifecycle.SavedStateHandle ) : T

    }