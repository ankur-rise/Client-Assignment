package com.mvvm_tutorial.di.components

import com.mvvm_tutorial.di.modules.DetailModule
import com.mvvm_tutorial.di.modules.NetworkModule
import com.mvvm_tutorial.di.modules.RepoModule
import com.mvvm_tutorial.di.modules.ViewModelAssistedFactoriesModule
import com.mvvm_tutorial.ui.DetailActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ NetworkModule::class, RepoModule::class, ViewModelAssistedFactoriesModule::class, DetailModule::class])
@Singleton
interface IActivityComponent {

    fun inject(activity: DetailActivity)

}