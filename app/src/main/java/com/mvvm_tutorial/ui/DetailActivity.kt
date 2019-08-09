package com.mvvm_tutorial.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mvvm_tutorial.R
import com.mvvm_tutorial.di.Injector
import com.mvvm_tutorial.ui.viewmodels.DetailViewModel
import com.mvvm_tutorial.ui.viewmodels.factory.ViewModelFactory
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory:ViewModelFactory

    val vm:DetailViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        val component = Injector.inject()
        component.inject(this)

    }

    override fun onStart() {
        super.onStart()

        vm.deliveryItems!!.observe(this, Observer {
            Log.i(DetailActivity::class.java.simpleName, it.size.toString())

        })

        vm.loadUser()
    }
}
