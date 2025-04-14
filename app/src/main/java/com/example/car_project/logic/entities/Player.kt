package com.example.car_project.logic.entities

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.example.car_project.R

class Player {
    private var row: Int = 0//get rows and cols of table
    private var col: Int = 0
    private lateinit var board: Array<Array<ImageView>>
    private lateinit var context: Context

    fun init(board: Array<Array<ImageView>>, context: Context) {
        this.board = board
        this.context = context
        row = board.size - 1//put the player in the middle and bottom of table (assume its odd number of cols
        col = board[0].size / 2
        draw()//
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
    fun fade() {
        getCell().animate()
            .alpha(0.3f)
            .setDuration(300)
            .withEndAction {
                getCell().animate().alpha(1f).setDuration(300).start()
            }.start()
    }


    private fun clear() {
        board[row][col].setImageDrawable(null)
    }

    fun getRow() = row
    fun getCol() = col
    fun getCell(): ImageView = board[row][col]
}
