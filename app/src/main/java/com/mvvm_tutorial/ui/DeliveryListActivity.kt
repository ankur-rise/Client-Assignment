package com.mvvm_tutorial.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mvvm_tutorial.R
import com.mvvm_tutorial.data.models.DeliveryItemDataModel
import com.mvvm_tutorial.di.Injector
import com.mvvm_tutorial.ui.adapter.DeliveryAdapter
import com.mvvm_tutorial.ui.viewmodels.DeliveryItemsViewModel
import com.mvvm_tutorial.ui.viewmodels.factory.ViewModelFactory
import javax.inject.Inject

class DeliveryListActivity : AppCompatActivity() {

    @Inject
    private lateinit var factory: ViewModelFactory

    private val viewModel: DeliveryItemsViewModel by viewModels { factory }
    private lateinit var recyclerView: RecyclerView
    lateinit var refreshLayout: SwipeRefreshLayout
    private var adapter = DeliveryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_delivery_list)

        val component = Injector.inject()
        component.inject(this)
        recyclerView = findViewById(R.id.rl)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        refreshLayout = findViewById(R.id.swipe_refresh)
        refreshLayout.setOnRefreshListener { refreshData() }


    }

    private fun refreshData() {
        viewModel.refreshData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.resultLiveData.observe(this, Observer<PagedList<DeliveryItemDataModel>> {
            Log.d("DeliveryListActivity", "list: ${it?.size}")
            if (refreshLayout.isRefreshing) {
                refreshLayout.setRefreshing(false)
            }
            adapter.submitList(it)
        })

        viewModel.errLiveData.observe(this, Observer {

            Toast.makeText(this, it, Toast.LENGTH_LONG).show()

        })

        viewModel.loadUser()

        recyclerView.adapter = adapter
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.menu.refresh_menu -> {
                refreshLayout.isRefreshing = true
                refreshData()
                return true
            }
        }

        return super.onOptionsItemSelected(item)

    }


}