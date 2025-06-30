package com.example.car_project.ui.dialogs

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.car_project.R
import com.example.car_project.logic.helpers.ScoreEntry
import com.example.car_project.logic.helpers.ScoreStorage
import com.example.car_project.ui.adapters.ScoreAdapter

object LeaderboardDialog {
    fun show(activity: AppCompatActivity, gameScore:Int, onClose: () -> Unit) {
        val layout = LayoutInflater.from(activity).inflate(R.layout.dialog_leaderboard, null)

        val recycler = layout.findViewById<RecyclerView>(R.id.leaderboard_recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = ScoreAdapter(ScoreStorage.loadScores(activity))

        val alertDialog = AlertDialog.Builder(activity)
            .setView(layout)
            .setCancelable(true)
            .create()

        // Cast layout so we can add views
        val layoutContainer = layout as? LinearLayout

        // If game has ended, allow adding a new score
        if (gameScore!=0) {
            val nameInput = EditText(activity).apply {
                hint = "Enter your name"
            }

            val saveButton = Button(activity).apply {
                text = "Save Score"
                setOnClickListener {
                    val name = nameInput.text.toString().ifBlank { "Player" }
                    val score = gameScore // Replace with actual game score
                    ScoreStorage.addScore(activity, ScoreEntry(name, score))
                    recycler.adapter = ScoreAdapter(ScoreStorage.loadScores(activity))
                    layoutContainer?.removeView(this)
                    layoutContainer?.removeView(nameInput)
                }
            }

            layoutContainer?.addView(nameInput)
            layoutContainer?.addView(saveButton)
        }

        // Back button
        val closeButton = Button(activity).apply {
            text = "Back"
            setOnClickListener {
                alertDialog.dismiss()
                onClose()
            }
        }

        layoutContainer?.addView(closeButton)

        alertDialog.show()
    }
}
