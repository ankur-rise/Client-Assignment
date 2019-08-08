package com.mvvm_tutorial.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mvvm_tutorial.data.repository.UserRepo
import com.mvvm_tutorial.ui.models.User
import java.lang.IllegalArgumentException
import javax.inject.Inject

class UserProfileViewModel @Inject
constructor(savedStateHandle: SavedStateHandle, userRepo: UserRepo) : ViewModel() {
    var userId:String = savedStateHandle.get("uid")?: throw IllegalArgumentException("Missing user id")
    var user: LiveData<User>? = userRepo.getUser(userId)
}