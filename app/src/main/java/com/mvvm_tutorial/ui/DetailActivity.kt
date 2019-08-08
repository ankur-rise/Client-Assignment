package com.mvvm_tutorial.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mvvm_tutorial.R
import com.mvvm_tutorial.data.cache.UserCache
import com.mvvm_tutorial.data.repository.UserRepo
import com.mvvm_tutorial.ui.viewmodels.UserDetailViewModel
import com.mvvm_tutorial.ui.viewmodels.factory.DetailViewModelFactory
import com.mvvm_tutorial.ui.viewmodels.factory.ViewModelFactory
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val vm:UserDetailViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("uid", "12345")
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()

        vm.userDetail.observe(this, Observer {
            Log.i(DetailActivity::class.java.simpleName, it.name + it.lastName)

        })

        vm.loadUser()
    }
}
