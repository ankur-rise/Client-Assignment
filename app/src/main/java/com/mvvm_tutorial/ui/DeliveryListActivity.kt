package com.mvvm_tutorial.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_tutorial.R
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.di.Injector
import com.mvvm_tutorial.ui.adapter.DeliveryAdapter
import com.mvvm_tutorial.ui.viewmodels.DeliveryItemsViewModel
import com.mvvm_tutorial.ui.viewmodels.factory.ViewModelFactory
import javax.inject.Inject

class DeliveryListActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    val viewModel: DeliveryItemsViewModel by viewModels { factory }
    lateinit var recyclerView: RecyclerView
    var adapter = DeliveryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_delivery_list)

        val component = Injector.inject()
        component.inject(this)
        recyclerView = findViewById(R.id.rl)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)


    }

    override fun onStart() {
        super.onStart()
        viewModel.loadUser().observe(this, Observer<PagedList<DeliveryItemDataModel>> {
            Log.d("DeliveryListActivity", "list: ${it?.size}")
//            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
        recyclerView.adapter = adapter
    }


}