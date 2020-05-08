package com.yun.maskdetector

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.MapsExperimentalFeature
import com.google.maps.android.ktx.awaitMap
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var map: GoogleMap

    @MapsExperimentalFeature
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        lifecycle.coroutineScope.launchWhenCreated {
            check(mapFragment != null)
            
            map = mapFragment.awaitMap()
            with(map) {
                moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, ZOOM_LEVEL))
                addMarker(MarkerOptions().position(SEOUL))
            }
        }
    }

    companion object {
        private val SEOUL = LatLng(37.566682, 126.978596)
        private const val ZOOM_LEVEL = 17f
    }
}