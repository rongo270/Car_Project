package com.example.car_project.logic.entities

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.example.car_project.R

class Player {

    private var row: Int = 0
    private var col: Int = 0
    private lateinit var board: Array<Array<ImageView>>
    private lateinit var context: Context

    fun init(board: Array<Array<ImageView>>, context: Context) {
        this.board = board
        this.context = context

        row = board.size - 1              // bottom row
        col = board[0].size / 2           // middle column (assuming odd count)

        draw()
    }

    fun move(deltaCol: Int) {
        val newCol = col + deltaCol
        if (newCol in 0 until board[0].size) {
            clear()
            col = newCol
            draw()
        }
    }

    private fun draw() {
        board[row][col].setImageDrawable(
            AppCompatResources.getDrawable(context, R.drawable.car)
        )
    }

    private fun clear() {
        board[row][col].setImageDrawable(null)
    }

    fun getCell(): ImageView = board[row][col]
}
