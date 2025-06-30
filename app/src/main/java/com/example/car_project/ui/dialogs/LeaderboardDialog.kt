package com.example.car_project.ui.dialogs

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.car_project.R
import com.example.car_project.logic.helpers.ScoreStorage
import com.example.car_project.ui.adapters.ScoreAdapter

object LeaderboardDialog {
    fun show(activity: AppCompatActivity, onClose: () -> Unit) {
        val layout = LayoutInflater.from(activity).inflate(R.layout.dialog_leaderboard, null)

        val recycler = layout.findViewById<RecyclerView>(R.id.leaderboard_recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = ScoreAdapter(ScoreStorage.loadScores(activity))

        val alertDialog = AlertDialog.Builder(activity)
            .setView(layout)
            .setCancelable(true)
            .create()

        val closeButton = Button(activity).apply {
            text = "Back"
            setOnClickListener {
                alertDialog.dismiss()
                onClose()
            }
        }

        (layout as? LinearLayout)?.addView(closeButton)

        alertDialog.show()
    }
}
