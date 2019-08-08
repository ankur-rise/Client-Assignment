package com.mvvm_tutorial.data

import androidx.lifecycle.LiveData
import com.mvvm_tutorial.ui.models.User

interface IUserRepo {

    fun getUser(userId: String): LiveData<User>

}