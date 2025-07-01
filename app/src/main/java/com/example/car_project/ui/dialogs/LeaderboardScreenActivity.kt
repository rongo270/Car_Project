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

        mapFragment = LeaderboardMapFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_scores, LeaderboardListFragment { lat, lng ->
                mapFragment.updateLocation(lat, lng)
            })
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_map, mapFragment)
            .commit()
    }
}
