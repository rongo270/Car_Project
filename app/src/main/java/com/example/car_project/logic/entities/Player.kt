package com.example.car_project.logic.entities

import android.content.Context
import android.media.MediaPlayer
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.example.car_project.R
import com.example.car_project.logic.managers.GameManager
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast


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

    fun add_effectPlayer(effectPlayer: MediaPlayer){
        //effectPlayer = MediaPlayer.create(context, R.raw.ouch_sound)
        effectPlayer.setOnCompletionListener { it.release() }
        effectPlayer.start()
    }

    fun move(deltaCol: Int, stones: List<Stone>, gameManager: GameManager, onHit: () -> Unit) {
        val newCol = col + deltaCol
        if (newCol in 0 until board[0].size) {//check if not out of bounds

            // Check if theres a stone in the new location
            val hitStone = stones.any { it.row == row && it.col == newCol }

            clear()//clear the player
            col = newCol
            draw()//draw the player in the now col

            if (hitStone) { //if hit stone return to manager the hit was made
                gameManager.checkIfHit(true)
                onHit()
                fade()
            }

        }
    }

     fun draw() {
        val cell = board[row][col]
        cell.setImageDrawable(
            AppCompatResources.getDrawable(context, R.drawable.steve)
        )
        cell.alpha = 1f
    }

    fun fade() {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(
            VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE)
        )
        Toast.makeText(context, "Ouch", Toast.LENGTH_SHORT).show()

        getCell().animate()
            .alpha(0.3f)
            .setDuration(200)
            .withEndAction {
                getCell().animate()
                    .alpha(1f)
                    .setDuration(200)
                    .start()
            }.start()
    }




    private fun clear() {
        board[row][col].setImageDrawable(null)
    }


    fun getRow() = row
    fun getCol() = col
    fun getCell(): ImageView = board[row][col]

}
