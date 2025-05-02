package com.example.car_project.ui.dialogs


import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.car_project.R
import com.example.car_project.utilities.gameSize.GameSize
import com.example.car_project.utilities.gameSize.Large
import com.example.car_project.utilities.gameSize.Medium
import com.example.car_project.utilities.gameSize.Small


object SizeSelect {

    fun showSizeSelectionDialog(context: Context, onSizeSelected: (GameSize) -> Unit) {
        val layout = LinearLayout(context).apply {//size
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)
            gravity = Gravity.CENTER
        }

        val btnSmall = AppCompatButton(context).apply { text = context.getString(R.string.size_small) }
        val btnMedium = AppCompatButton(context).apply { text = context.getString(R.string.size_medium) }
        val btnLarge = AppCompatButton(context).apply { text = context.getString(R.string.size_large) }


        layout.addView(btnSmall)
        layout.addView(btnMedium)
        layout.addView(btnLarge)

        val dialog = AlertDialog.Builder(context)
            .setTitle("Select Game Size")
            .setView(layout)
            .setCancelable(false)
            .create()

        //listen and build
        btnSmall.setOnClickListener {
            onSizeSelected(Small)
            dialog.dismiss()
        }

        btnMedium.setOnClickListener {
            onSizeSelected(Medium)
            dialog.dismiss()
        }

        btnLarge.setOnClickListener {
            onSizeSelected(Large)
            dialog.dismiss()
        }

        dialog.show()
    }
}