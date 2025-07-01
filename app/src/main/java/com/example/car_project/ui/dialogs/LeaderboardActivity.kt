package com.example.car_project.ui.dialogs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.car_project.R

class LeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard_screen)

        // Create the map fragment once, so we can call updateLocation on it
        val mapFragment = LeaderboardMapFragment()

        // Pass a lambda to update the map when a score is selected
        val listFragment = LeaderboardListFragment { lat, lng ->
            mapFragment.updateLocation(lat, lng)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_scores, listFragment)
            .replace(R.id.fragment_map, mapFragment)
            .commit()
    }
}
