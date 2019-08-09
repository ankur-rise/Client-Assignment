package com.mvvm_tutorial.di.modules

import com.mvvm_tutorial.data.IDeliveryRepo
import com.mvvm_tutorial.data.repository.DeliveryRepo
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {

    @Binds
    abstract fun getRepo(repo:DeliveryRepo): IDeliveryRepo



}