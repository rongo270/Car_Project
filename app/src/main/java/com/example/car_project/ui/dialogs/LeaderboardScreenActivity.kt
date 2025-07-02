package com.example.car_project.ui.dialogs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.car_project.R

class LeaderboardScreenActivity : AppCompatActivity() {

    private lateinit var mapFragment: LeaderboardMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("LeaderboardScreenActivity", "onCreate started")
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_leaderboard_screen)
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_map)
        (fragment as? LeaderboardMapFragment)?.mapView?.onCreate(savedInstanceState)

        // First, add the map fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_map, LeaderboardMapFragment())
            .commit()

        // Then, add the score list with click handler that finds the real map fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_scores, LeaderboardListFragment { lat, lng ->
                val fragment = supportFragmentManager.findFragmentById(R.id.fragment_map)
                if (fragment is LeaderboardMapFragment) {
                    fragment.updateLocation(lat, lng)
                } else {
                    Log.d("LeaderboardScreenActivity", "Map fragment not found or wrong type")
                }
            })
            .commit()
    }
}

