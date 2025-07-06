package com.example.car_project.logic.LeaderBoard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.car_project.R
import com.example.car_project.logic.Fragments.LeaderboardListFragment
import com.example.car_project.logic.Fragments.LeaderboardMapFragment

class LeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard_screen)

        //Create the Map Fragment
        val mapFragment = LeaderboardMapFragment()

        //Create the Leaderboard Fragment
        val listFragment = LeaderboardListFragment { lat, lng ->
            mapFragment.updateLocation(lat, lng)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_scores, listFragment)
            .replace(R.id.fragment_map, mapFragment)
            .commit()
    }
}
