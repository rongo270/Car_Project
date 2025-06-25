package com.example.car_project.logic.entities

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.example.car_project.R
import com.example.car_project.logic.managers.GameManager

class Stone(var row: Int, var col: Int, stoneLevel:Int, var isCoin:Boolean = false) {

    //Stone Levels
    private val drawableRes = if (isCoin) {
        R.drawable.diamond
    } else {
        when (stoneLevel) {
            1 -> R.drawable.creeper
            2 -> R.drawable.black_skeleton
            3 -> R.drawable.enderman
            else -> R.drawable.creeper
        }
    }

    fun drawStone(board: Array<Array<ImageView>>, context: Context) {//draw stone
        board[row][col].setImageDrawable(
            AppCompatResources.getDrawable(context, drawableRes)
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
               gameManager: GameManager, onHit: (isCoin:Boolean) -> Unit): Boolean {
        return if (gameManager.didCollide(this, player)) {//check if collide
            clear(board)//clear img from board
            onHit(this.isCoin)
            false //return
        } else {
            moveDown(board, context) //If don't move stone down
        }
    }

    private fun clear(board: Array<Array<ImageView>>) {
        board[row][col].setImageDrawable(null)
    }


    fun getNextRow(): Int = row + 1
}
