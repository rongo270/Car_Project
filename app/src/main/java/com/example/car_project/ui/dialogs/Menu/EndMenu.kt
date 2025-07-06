package com.example.car_project.ui.dialogs.Menu

import android.content.Context
import android.app.AlertDialog
import android.content.Intent
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.car_project.logic.helpers.Score.ScoreEntry
import com.example.car_project.ui.dialogs.LeaderboardScreenActivity

object EndMenu {

    fun show(context: Context, gameScore: Int, qualifies: Boolean, user: ScoreEntry?)
    {
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)
            gravity = Gravity.CENTER
        }

        val btnRestart = AppCompatButton(context).apply {
            text = "New Game"
        }

        val btnQuit = AppCompatButton(context).apply {
            text = "Quit"
        }

        val btnLeaderboard = AppCompatButton(context).apply {
            text = "Leaderboard"
        }

        layout.addView(btnLeaderboard)
        layout.addView(btnRestart)
        layout.addView(btnQuit)

        val dialog = AlertDialog.Builder(context)
            .setTitle("Game Over")
            .setView(layout)
            .setCancelable(false)
            .create()

        btnRestart.setOnClickListener {
            val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
            intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            Runtime.getRuntime().exit(0) // Force full app restart
        }

        btnQuit.setOnClickListener {
            Runtime.getRuntime().exit(0)
        }

        btnLeaderboard.setOnClickListener {
            if (context is AppCompatActivity) {
                //ScoreStorage.addScore(context, ScoreEntry("Player", gameScore, 32.0853, 34.7818))
                //ScoreStorage.clearScores(context)
                context.startActivity(Intent(context, LeaderboardScreenActivity::class.java))
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}