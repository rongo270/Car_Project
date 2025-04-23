package com.example.car_project

import android.content.Context
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import com.example.car_project.logic.entities.Player
import com.example.car_project.utilities.BoardConfig

class GameBoard(
    private val context: Context,
    private val gridLayout: GridLayout,
    private val config: BoardConfig
) {
    private val board: Array<Array<ImageView>> =
        Array(config.rows) { Array(config.cols) { ImageView(context) } }

    private lateinit var player: Player

    fun initBoard(player: Player) {
        this.player = player
        gridLayout.removeAllViews()
        gridLayout.rowCount = config.rows
        gridLayout.columnCount = config.cols

        for (row in 0 until config.rows) {
            for (col in 0 until config.cols) {
                val imageView = ImageView(context).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = config.cellWidth
                        height = config.cellHeight
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
    fun getCols(): Int = config.cols
}

