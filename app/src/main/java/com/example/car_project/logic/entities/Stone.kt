package com.example.car_project.logic.entities

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.example.car_project.R

class Stone(var row: Int, var col: Int) {

    fun draw(board: Array<Array<ImageView>>, context: Context) {
        board[row][col].setImageDrawable(
            AppCompatResources.getDrawable(context, R.drawable.stone)
        )
    }

    fun clear(board: Array<Array<ImageView>>) {
        board[row][col].setImageDrawable(null)
    }

    fun moveDown(board: Array<Array<ImageView>>, context: Context): Boolean {
        clear(board)
        row++
        return if (row < board.size) {
            draw(board, context)
            true
        } else {
            false // reached bottom
        }
    }

    fun getNextRow(): Int = row + 1
}
