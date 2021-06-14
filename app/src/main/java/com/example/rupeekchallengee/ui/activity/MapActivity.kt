package com.example.rupeekchallengee.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rupeekchallengee.R
import com.example.rupeekchallengee.data.apimodel.PlaceOfInterestModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : AppCompatActivity() , OnMapReadyCallback{
    var mapFragment: SupportMapFragment? = null
    var bundle : PlaceOfInterestModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        bundle = intent.getSerializableExtra("Data") as PlaceOfInterestModel
        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    override fun onMapReady(it: GoogleMap) {
        if(bundle!=null)
        {
            val latLang = LatLng((bundle!!.latitude.subSequence(0,7)).toString().toDouble(),(bundle!!.longitude.subSequence(0,7)).toString().toDouble())
            it.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            it.addMarker(
                MarkerOptions()
                    .position(latLang)
                    .title(bundle!!.name)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            )
            val zoomLevel = 6.0f
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLang, zoomLevel))
        }
    }
}