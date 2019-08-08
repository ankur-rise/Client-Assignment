package com.mvvm_tutorial.di.modules

import androidx.lifecycle.ViewModel
import com.mvvm_tutorial.di.factory.ViewModelAssistedFactory
import com.mvvm_tutorial.di.key.ViewModelKey
import com.mvvm_tutorial.ui.viewmodels.UserDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

//@Module
abstract class UserDetailModule {
   /* @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    abstract fun bindFactory( vm: UserDetailViewModel.Factory): ViewModelAssistedFactory<out ViewModel>*/

}