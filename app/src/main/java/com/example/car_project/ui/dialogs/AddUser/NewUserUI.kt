package com.example.car_project.ui.dialogs.AddUser

import android.content.Context
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.car_project.R
import com.example.car_project.logic.helpers.Score.ScoreEntry
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

object NewUserUI {

    fun show(context: Context, score: Int, callback: (ScoreEntry) -> Unit) {
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)
            gravity = Gravity.CENTER
        }

        val nameInput = EditText(context).apply {
            hint = "Enter your name"
        }

        //Start to build a map and a Default place to look
        val mapView = MapView(context)
        mapView.onCreate(null)
        mapView.onResume()

        var selectedLocation = LatLng(32.0853, 34.7818) //Default: Tel Aviv

        //Load map to chose place
        mapView.getMapAsync { googleMap ->
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedLocation, 10f))

            //Gets map location coordinates
            googleMap.setOnMapClickListener { latLng ->
                googleMap.clear()
                googleMap.addMarker(MarkerOptions().position(latLng))
                selectedLocation = latLng
            }
        }

        val saveButton = AppCompatButton(context).apply {
            text = context.getString(R.string.save_button)
        }

        // Add views to layout
        layout.addView(nameInput)
        layout.addView(mapView, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            600
        ))
        layout.addView(saveButton)

        //Only show if top 10, check is not here
        val dialog = AlertDialog.Builder(context)
            .setTitle("Top 10! Enter Name & Location")
            .setView(layout)
            .setCancelable(false)
            .create()

        //Build a user high score
        saveButton.setOnClickListener {
            val entry = ScoreEntry(
                name = nameInput.text.toString().ifBlank { "Player" },
                score = score,
                latitude = selectedLocation.latitude,
                longitude = selectedLocation.longitude
            )
            callback(entry)
            dialog.dismiss()
        }

        dialog.show()
    }
}
