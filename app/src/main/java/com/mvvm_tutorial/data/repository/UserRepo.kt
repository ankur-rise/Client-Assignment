package com.mvvm_tutorial.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm_tutorial.data.IUserRepo
import com.mvvm_tutorial.data.cache.UserCache
import com.mvvm_tutorial.data.network.Apis
import com.mvvm_tutorial.ui.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepo @Inject constructor(private val webservice: Apis?,
                                   private val userCache: UserCache):IUserRepo  {


  override fun getUser(userId: String): LiveData<User> {
        // This isn't an optimal implementation. We'll fix it later.
        val cacheData = userCache.get(userId)
        if(cacheData!=null) {
            return cacheData
        }
        val data = MutableLiveData<User>()
        userCache.set(userId, data)
        /*webservice!!.getUser(userId).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                data.value = response.body()
            }
            // Error case is left out for brevity.
            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO()
            }
        })*/
        return data
    }
}
