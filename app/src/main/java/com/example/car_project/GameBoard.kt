package com.example.car_project.board
import android.content.Context
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources

class GameBoard(
    private val context: Context,
    private val gridLayout: GridLayout,
    private val rows: Int = 6,
    private val cols: Int = 3
) {
    private val board: Array<Array<ImageView>> = Array(rows) {
        Array(cols) { ImageView(context) }
    }

    private var playerRow = rows - 1
    private var playerCol = 1

    fun initBoard(playerDrawable: Int) {
        gridLayout.removeAllViews()
        gridLayout.rowCount = rows
        gridLayout.columnCount = cols

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val imageView = ImageView(context).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = 100
                        height = 100
                        setMargins(8, 8, 8, 8)
                    }
                    id = View.generateViewId()
                }

                board[row][col] = imageView
                gridLayout.addView(imageView)
            }
        }

        // הוספת תמונת שחקן מה-vector
        board[playerRow][playerCol].setImageDrawable(
            AppCompatResources.getDrawable(context, playerDrawable)
        )
    }

}