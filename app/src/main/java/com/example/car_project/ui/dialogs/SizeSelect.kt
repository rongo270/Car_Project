//package com.example.car_project.ui.dialogs
//
//
//import android.content.Context
//import android.view.Gravity
//import android.widget.LinearLayout
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.widget.AppCompatButton
//
//
//object SizeSelect {
//
//    fun showSizeSelectionDialog(context: Context, onSizeSelected: (String) -> Unit) {
//        val layout = LinearLayout(context).apply {
//            orientation = LinearLayout.VERTICAL
//            setPadding(40, 40, 40, 40)
//            gravity = Gravity.CENTER
//        }
//
//        val btnSmall = AppCompatButton(context).apply { text = "Small" }
//        val btnMedium = AppCompatButton(context).apply { text = "Medium" }
//        val btnLarge = AppCompatButton(context).apply { text = "Large" }
//
//        layout.addView(btnSmall)
//        layout.addView(btnMedium)
//        layout.addView(btnLarge)
//
//        val dialog = AlertDialog.Builder(context)
//            .setTitle("Select Game Size")
//            .setView(layout)
//            .setCancelable(false)
//            .create()
//
//        btnSmall.setOnClickListener {
//            onSizeSelected("small")
//            dialog.dismiss()
//        }
//
//        btnMedium.setOnClickListener {
//            onSizeSelected("medium")
//            dialog.dismiss()
//        }
//
//        btnLarge.setOnClickListener {
//            onSizeSelected("large")
//            dialog.dismiss()
//        }
//
//        dialog.show()
//    }
//}