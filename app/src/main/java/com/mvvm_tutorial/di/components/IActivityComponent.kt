package com.mvvm_tutorial.di.components

import com.mvvm_tutorial.di.modules.*
import com.mvvm_tutorial.ui.DeliveryListActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DaoModule::class, NetworkModule::class, RepoModule::class, ViewModelAssistedFactoriesModule::class, DetailModule::class])
@Singleton
interface IActivityComponent {

    fun inject(activity: DeliveryListActivity)

}