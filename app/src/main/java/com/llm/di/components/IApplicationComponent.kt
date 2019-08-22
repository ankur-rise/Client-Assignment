package com.llm.di.components

import com.llm.di.modules.*
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, ContextModule::class, DaoModule::class, NetworkModule::class, RepoModule::class, ViewModelAssistedFactoriesModule::class, DetailModule::class])
@Singleton
interface IApplicationComponent {
    fun plusActivityComponent(): IActivityComponent
}