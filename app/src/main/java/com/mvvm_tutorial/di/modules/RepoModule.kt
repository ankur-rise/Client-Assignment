package com.mvvm_tutorial.di.modules

import com.mvvm_tutorial.data.IUserRepo
import com.mvvm_tutorial.data.cache.UserCache
import com.mvvm_tutorial.data.repository.UserRepo
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepoModule {

    @Binds
    abstract fun getRepo(repo:UserRepo): IUserRepo

}