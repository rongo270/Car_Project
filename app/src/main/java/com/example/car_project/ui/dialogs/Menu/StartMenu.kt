package com.example.car_project.ui.dialogs.Menu


import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.car_project.R
import com.example.car_project.logic.LeaderBoard.LeaderboardScreenActivity
import com.example.car_project.utilities.gameSize.GameSize
import com.example.car_project.utilities.gameSize.Large
import com.example.car_project.utilities.gameSize.Medium
import com.example.car_project.utilities.gameSize.Small


object StartMenu {

    fun startUI(
        context: Context,
        onSizeSelected: (GameSize, useTilt: Boolean, useArrows: Boolean) -> Unit
    ) {
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)
            gravity = Gravity.CENTER
        }

        val btnSmall =
            AppCompatButton(context).apply { text = context.getString(R.string.size_small) }
        val btnMedium =
            AppCompatButton(context).apply { text = context.getString(R.string.size_medium) }
        val btnLarge =
            AppCompatButton(context).apply { text = context.getString(R.string.size_large) }

        val tiltCheckbox = CheckBox(context).apply {
            text = context.getString(R.string.control_tilt)
            isChecked = false
        }

        val arrowsCheckbox = CheckBox(context).apply {
            text = context.getString(R.string.control_arrows)
            isChecked = true
        }

        val controlLayout = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            addView(tiltCheckbox)
            addView(arrowsCheckbox)
        }

        val btnLeaderboard = AppCompatButton(context).apply {
            text = context.getString(R.string.leaderboard)
        }

        layout.addView(btnSmall)
        layout.addView(btnMedium)
        layout.addView(btnLarge)
        layout.addView(controlLayout)
        layout.addView(btnLeaderboard)

        val dialog = AlertDialog.Builder(context)
            .setTitle("Select Game Mode")
            .setView(layout)
            .setCancelable(false)
            .create()

            btnSmall.setOnClickListener {
                if(tiltCheckbox.isChecked || arrowsCheckbox.isChecked) {
                    onSizeSelected(Small, tiltCheckbox.isChecked, arrowsCheckbox.isChecked)
                    dialog.dismiss()
                }
            }

            btnMedium.setOnClickListener {
                if(tiltCheckbox.isChecked || arrowsCheckbox.isChecked) {
                    onSizeSelected(Medium, tiltCheckbox.isChecked, arrowsCheckbox.isChecked)
                    dialog.dismiss()
                }
            }

            btnLarge.setOnClickListener {
                if(tiltCheckbox.isChecked || arrowsCheckbox.isChecked) {
                    onSizeSelected(Large, tiltCheckbox.isChecked, arrowsCheckbox.isChecked)
                    dialog.dismiss()
                }
            }

        btnLeaderboard.setOnClickListener {
            if (context is AppCompatActivity) {
                context.startActivity(Intent(context, LeaderboardScreenActivity::class.java))
            }
        }



        dialog.show()
    }
}