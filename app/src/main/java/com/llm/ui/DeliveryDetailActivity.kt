package com.llm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.llm.R
import com.llm.data.models.DeliveryItemDataModel
import com.llm.data.models.LatLongDataModel
import com.llm.databinding.ActivityDeliveyDetailBinding
import com.llm.di.Injector
import com.llm.ui.viewmodels.DetailViewModel
import javax.inject.Inject

class DeliveryDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var coordinates:LatLongDataModel

    @Inject                    //by lazy { ViewModelProviders.of(this).get(DetailViewModel::class.java)}
    lateinit var viewModel:DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = Injector.inject()
        component.inject(this)

        val dataBinding: ActivityDeliveyDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_delivey_detail)
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val bundle = intent.extras
        val model = bundle.getSerializable(KEY_DELIVERY_ITEM) as DeliveryItemDataModel


        viewModel.loadData(model)

        coordinates = model.location

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val location = LatLng(coordinates.lat, coordinates.lng)
        mMap.addMarker(MarkerOptions().position(location).title(coordinates.address))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(coordinates.lat, coordinates.lng), 15f))
    }
}
