package com.llm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.MapView
import com.llm.R
import com.llm.data.models.DeliveryItemDataModel
import com.llm.databinding.ActivityDeliveyDetailBinding
import com.llm.di.Injector
import com.llm.ui.viewmodels.DetailViewModel
import javax.inject.Inject


class DeliveryDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = Injector.inject()
        component.inject(this)

        val dataBinding: ActivityDeliveyDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_delivey_detail)
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // setting title
        supportActionBar?.title = getString(R.string.delivery_detail)

        val mapView = findViewById<MapView>(R.id.map_view)
        mapView.onCreate(null)

        val bundle = intent.extras
        val model = bundle!!.getParcelable(KEY_DELIVERY_ITEM) as DeliveryItemDataModel
        viewModel.loadData(model)

    }

}
