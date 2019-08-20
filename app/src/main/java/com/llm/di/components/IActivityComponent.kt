package com.llm.di.components

import com.llm.di.modules.*
import com.llm.ui.DeliveryListActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DaoModule::class, NetworkModule::class, RepoModule::class, ViewModelAssistedFactoriesModule::class, DetailModule::class])
@Singleton
interface IActivityComponent {

    fun inject(activity: DeliveryListActivity)

}