package com.example.car_project.ui.dialogs


import android.content.Context
import android.view.Gravity
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.car_project.R
import com.example.car_project.utilities.gameSize.GameSize
import com.example.car_project.utilities.gameSize.Large
import com.example.car_project.utilities.gameSize.Medium
import com.example.car_project.utilities.gameSize.Small


object SizeSelect {

    fun showSizeSelectionDialog(
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

        layout.addView(btnSmall)
        layout.addView(btnMedium)
        layout.addView(btnLarge)

        val tiltCheckbox = CheckBox(context).apply {
            text = context.getString(R.string.control_tilt)
            isChecked = true
        }

        val arrowsCheckbox = CheckBox(context).apply {
            text = context.getString(R.string.control_arrows)
            isChecked = false
        }

        val controlLayout = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            addView(tiltCheckbox)
            addView(arrowsCheckbox)
        }

        layout.addView(controlLayout)

        val dialog = AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.select_game_mode))
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

        dialog.show()
    }
}