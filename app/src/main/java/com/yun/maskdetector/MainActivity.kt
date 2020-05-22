package com.yun.maskdetector

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.MapsExperimentalFeature
import com.yun.maskdetector.model.RemainState
import org.koin.androidx.viewmodel.ext.android.viewModel
import pub.devrel.easypermissions.EasyPermissions


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private val viewModel: MainViewModel by viewModel()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var map: GoogleMap

    @MapsExperimentalFeature
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        viewModel.stores.observe(this, Observer {
            it?.forEach { store ->
                val latLng = LatLng(store.lat.toDouble(), store.lng.toDouble())
                val remainState = RemainState.getRemainState(store.remain_stat)
                val state = remainState?.run { getString(stringId) }
                val color = remainState?.run { colorId }
                map.addMarker(
                    MarkerOptions()
                        .title(store.name)
                        .snippet(state)
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                )
            }

        })
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap ?: return
        enableLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
        getLastLocation()
    }

    private fun enableLocation() {
        if (hasLocationPermission()) {
            getLastLocation()
        } else {
            EasyPermissions.requestPermissions(
                this, getString(R.string.location),
                REQUEST_CODE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    private fun hasLocationPermission(): Boolean {
        return EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnCompleteListener { taskLocation ->
                if (taskLocation.isSuccessful && taskLocation.result != null) {
                    taskLocation.result?.run {
                        val latLng = LatLng(latitude, longitude)
                        updateLocationUI(latLng)
                        viewModel.fetchStores(latitude.toFloat(), longitude.toFloat(), 1000)
                    }
                }
            }
    }

    private fun updateLocationUI(latLng: LatLng) {
        map.run {
            isMyLocationEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
            moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL))
        }
    }

    companion object {
        private val SEOUL = LatLng(37.566682, 126.978596)
        private const val ZOOM_LEVEL = 17f
        const val REQUEST_CODE_LOCATION = 1000
    }
}