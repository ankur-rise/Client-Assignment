package com.mvvm_tutorial.data.cache

import androidx.lifecycle.LiveData
import com.mvvm_tutorial.ui.models.User
import javax.inject.Inject

class UserCache @Inject constructor() {
val map = HashMap<String, LiveData<User>>()

    fun set(userId:String, user:LiveData<User>) {
        map.put(userId, user)
    }

    fun get(userId:String) :LiveData<User>? {
        return map.get(userId)
    }

}