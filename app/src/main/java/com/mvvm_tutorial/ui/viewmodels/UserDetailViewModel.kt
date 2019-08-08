package com.mvvm_tutorial.ui.viewmodels

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mvvm_tutorial.data.repository.UserRepo
import com.mvvm_tutorial.di.factory.ViewModelAssistedFactory
import com.mvvm_tutorial.ui.models.User
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class UserDetailViewModel @AssistedInject constructor(val repo:UserRepo,
                                                      @Assisted savedState:SavedStateHandle) : ViewModel() {

    val uid:String? = savedState["uid"]
    val userDetail:MutableLiveData<User> = MutableLiveData()
    var counter:Int = 0
    fun loadUser() {
        if(repo!=null){
            Log.i(UserDetailViewModel::class.java.simpleName, "repo instance created")
        }
        counter++
        Log.i(UserDetailViewModel::class.java.simpleName, this.toString().plus(" ").plus(counter))
        val user = User(uid.plus(" Ankur"), "Chaudhary".plus(SystemClock.currentThreadTimeMillis()))
        userDetail.value = user
    }

    @AssistedInject.Factory
    public abstract interface Factory: ViewModelAssistedFactory<UserDetailViewModel>
}

