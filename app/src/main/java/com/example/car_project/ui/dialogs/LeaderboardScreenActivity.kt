package com.example.car_project.ui.dialogs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.car_project.R

class LeaderboardScreenActivity : AppCompatActivity() {

    private lateinit var mapFragment: LeaderboardMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LeaderboardScreenActivity", "onCreate started")
        setContentView(R.layout.activity_leaderboard_screen)

        mapFragment = LeaderboardMapFragment()

        // Load leaderboard list with callback to update map
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_scores, LeaderboardListFragment { lat, lng ->
                mapFragment.updateLocation(lat, lng)
            })
            .commit()

        // Load map fragment initially (keeps it loaded)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_map, mapFragment)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()  // Call the parent method
        finish()               // Then close the activity
    }

}
