package com.example.car_project.logic.entities

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.example.car_project.R
import com.example.car_project.logic.managers.GameManager

class Stone(var row: Int, var col: Int) {


    fun drawStone(board: Array<Array<ImageView>>, context: Context) {//draw stone
        board[row][col].setImageDrawable(
            AppCompatResources.getDrawable(context, R.drawable.stone)
        )
    }

    private fun moveDown(board: Array<Array<ImageView>>, context: Context): Boolean {//move stone down
        clear(board)//clear image
        row++
        return if (row < board.size) {
            drawStone(board, context)//draw image
            true
        } else {
            false // reached bottom
        }
    }

    fun update(board: Array<Array<ImageView>>, context: Context, player: Player,
               gameManager: GameManager, onHit: () -> Unit): Boolean {
        return if (gameManager.didCollide(this, player)) {//check if collide
            clear(board)//clear img from board
            onHit()
            false //return
        } else {
            moveDown(board, context) // if dont move stone down
        }
    }

    private fun clear(board: Array<Array<ImageView>>) {
        board[row][col].setImageDrawable(null)
    }

    fun getNextRow(): Int = row + 1
}
