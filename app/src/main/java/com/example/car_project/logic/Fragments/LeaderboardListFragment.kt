package com.example.car_project.logic.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.car_project.R
import com.example.car_project.logic.helpers.Score.ScoreStorage

class LeaderboardListFragment(
    private val onMapRequested: (Double, Double) -> Unit
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 20, 20, 20)
        }

        val scores = ScoreStorage.loadScores(requireContext())

        //Build LeaderBoard
        scores.forEach { scoreEntry ->
            val entryLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                val nameView = TextView(requireContext()).apply {
                    text = getString(R.string.score_entry_format, scoreEntry.name, scoreEntry.score)
                    layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                }

                val btn = Button(requireContext()).apply {
                    text = getString(R.string.show_map)
                    setOnClickListener {
                        onMapRequested(scoreEntry.latitude, scoreEntry.longitude)
                    }
                }

                addView(nameView)
                addView(btn)
            }

            layout.addView(entryLayout)
        }

        return layout
    }
}
