package com.example.car_project.ui.dialogs

object SizeSelect {
    private fun showSizeSelectionDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_select_size, null)
        val dialog = Dialog(this)
        dialog.setContentView(dialogView)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogView.findViewById<Button>(R.id.btn_small).setOnClickListener {
            dialog.dismiss()
            startGameWithSize(Small)
        }

        dialogView.findViewById<Button>(R.id.btn_medium).setOnClickListener {
            dialog.dismiss()
            startGameWithSize(Medium)
        }

        dialogView.findViewById<Button>(R.id.btn_large).setOnClickListener {
            dialog.dismiss()
            startGameWithSize(Large)
        }

        dialog.show()
    }

}