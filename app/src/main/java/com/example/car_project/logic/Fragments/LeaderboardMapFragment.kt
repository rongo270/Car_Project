package com.example.car_project.logic.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.car_project.R

class LeaderboardMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap //Google Map
    var mapView: MapView? = null //MapView container from XML

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_leaderboard_map, container, false)
        mapView = view.findViewById(R.id.map_view) //Get the map view
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this) //Start load map
        return view
    }

    //after load map set the location to tel aviv
    override fun onMapReady(googleMap: GoogleMap) {
        Log.d("LeaderboardMapFragment", "onMapReady called")
        map = googleMap
        val defaultLocation = LatLng(32.0853, 34.7818) // Tel Aviv by default
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f))
    }

    //Get location and focus to this place
    fun updateLocation(lat: Double, lng: Double) {
        Log.d("LeaderboardMapFragment", "updateLocation called with: $lat, $lng")
        if (::map.isInitialized) {
            val location = LatLng(lat, lng)
            map.clear() // Remove old markers
            map.addMarker(MarkerOptions().position(location).title("Score Location")) // Mark new
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f)) // Zoom to it
        } else {
            Log.d("LeaderboardMapFragment", "Map is NOT initialized yet")
        }
    }

    //Forward MapView lifecycle to match fragment
    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        mapView?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView?.onDestroy()
        super.onDestroy()
    }

    @Deprecated("Deprecated due to ComponentCallbacks")
    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }
}
