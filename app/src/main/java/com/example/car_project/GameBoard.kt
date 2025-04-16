package com.example.car_project.board

import android.content.Context
import android.view.View
import com.example.car_project.utilities.Constants
import android.widget.GridLayout
import android.widget.ImageView
import com.example.car_project.logic.entities.Player

class GameBoard(
    private val context: Context,
    private val gridLayout: GridLayout,
    private val rows: Int = Constants.ROWS,
    private val cols: Int = Constants.COLS
) {
    private val board: Array<Array<ImageView>> = Array(rows) {
        Array(cols) { ImageView(context) }
    }

    private lateinit var player: Player

    fun initBoard(player: Player) {
        this.player = player
        gridLayout.removeAllViews()
        gridLayout.rowCount = rows
        gridLayout.columnCount = cols

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val imageView = ImageView(context).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = Constants.BOARD_WIDTH
                        height = Constants.BOARD_HEIGHT
                        setMargins(8, 8, 8, 8)
                    }
                    id = View.generateViewId()
                }
                board[row][col] = imageView
                gridLayout.addView(imageView)
            }
        }

        player.init(board, context)
    }



    fun getPlayer(): Player = player
    fun getBoard(): Array<Array<ImageView>> = board
    fun getCols(): Int = cols
}
