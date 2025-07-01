package com.example.car_project.ui.dialogs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.car_project.R
import com.example.car_project.logic.helpers.ScoreStorage
import com.example.car_project.ui.adapters.ScoreAdapter

class LeaderboardListFragment(
    private val onScoreSelected: (Double, Double) -> Unit
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_leaderboard_list, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_scores)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val scores = ScoreStorage.loadScores(requireContext())
        recycler.adapter = ScoreAdapter(scores) { score ->
            onScoreSelected(score.latitude, score.longitude)
        }

        return view
    }
}
