package com.example.car_project.logic.entities

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.example.car_project.R
import com.example.car_project.logic.managers.GameManager
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import com.example.car_project.logic.managers.StoneManager
import com.example.car_project.sound.SoundEffectManager
import android.Manifest
import androidx.core.content.ContextCompat


class Player {
    private var row: Int = 0//Gets rows and cols of table
    private var col: Int = 0
    private lateinit var board: Array<Array<ImageView>>
    private lateinit var context: Context
    private lateinit var soundEffect: SoundEffectManager



    fun init(board: Array<Array<ImageView>>, context: Context) {
        this.board = board
        this.context = context
        soundEffect = SoundEffectManager()
        row = board.size - 1//Puts the player in the middle and bottom of table (assume its odd number of cols)
        col = board[0].size / 2
        draw()//
    }


    fun move(deltaCol: Int, gameManager: GameManager, onHit: () -> Unit) {
        val stones = StoneManager.getStones()
        val newCol = col + deltaCol
        if (newCol in 0 until board[0].size) {//Check if not out of bounds

            //Check if theres a stone in the new location
            val hitStone = stones.any { it.row == row && it.col == newCol }

            clear()//Clear the player
            col = newCol
            draw()//Draw the player in the now col

            if (hitStone) { //If hit stone return to manager the hit was made
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
        vibrate(context)
        Toast.makeText(context, "Ouch", Toast.LENGTH_SHORT).show()
        soundEffect.hitMedia(context)

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


//________________________________________VIBRATE_______________________________________________\\
    private fun vibrate(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator ?: return

        if (!vibrator.hasVibrator()) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) { //Android 12 (API 31) and newer
            val hasPermission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.VIBRATE
            ) == PackageManager.PERMISSION_GRANTED

            if (hasPermission) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE)
                )
            }
        } else {
            //Android 11 and below
            vibrator.vibrate(
                VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE)
            )
        }
    }


    private fun clear() {
        board[row][col].setImageDrawable(null)
    }


    fun getRow() = row
    fun getCol() = col
    private fun getCell(): ImageView = board[row][col]


}
