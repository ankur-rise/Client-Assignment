package com.llm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.llm.R
import com.llm.data.models.DeliveryItemDataModel
import com.llm.data.models.LatLongDataModel

class DeliveryDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var coordinates:LatLongDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivey_detail)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        val bundle = intent.extras
        val model = bundle.getSerializable(KEY_DELIVERY_ITEM) as DeliveryItemDataModel

        val textView:TextView =  findViewById(R.id.tv_desc)
        textView.text = model.description

        val iv:SimpleDraweeView  = findViewById(R.id.iv)
        iv.setImageURI(model.imageUrl?:"")

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
