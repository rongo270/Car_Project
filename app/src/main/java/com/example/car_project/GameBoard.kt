package com.example.car_project.board

import android.content.Context
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.example.car_project.R
import com.example.car_project.logic.entities.Player

class GameBoard(
    private val context: Context,
    private val gridLayout: GridLayout,
    private val rows: Int = 6,
    private val cols: Int = 3
) {
    private val board: Array<Array<ImageView>> = Array(rows) {
        Array(cols) { ImageView(context) }
    }

    private lateinit var player: Player


    fun initBoard(playerDrawable: Player) {
        this.player = player
        gridLayout.removeAllViews()
        gridLayout.rowCount = rows
        gridLayout.columnCount = cols

        fun initBoard(player: Player) {
            this.player = player
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

            player.init(board, context)// let the player place himself
        }
    }
    fun movePlayer(deltaCol: Int) {
        player.move(deltaCol)
    }
}