package com.example.car_project.logic.gameflow

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.car_project.logic.entities.Player
import com.example.car_project.logic.helpers.speedChanger.adjustSpeed
import com.example.car_project.logic.managers.GameManager
import com.example.car_project.logic.managers.StoneManager
import com.example.car_project.utilities.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.example.car_project.GameBoard

object GameLoop {

    fun startGameLoop(
        lifecycleScope: LifecycleCoroutineScope,
        context: Context,
        player: Player,
        gameBoard: GameBoard,
        gameManager: GameManager,
        mainHearts: Array<AppCompatImageView>,
        speedStart: Long
    ) {
        lifecycleScope.launch {
            var tick = 0
            var speed = speedStart

            while (true) {
                tick++

                gameManager.updateScore(Constants.POINT_FOR_SECOND)

                StoneManager.moveAll(
                    board = gameBoard.getBoard(),
                    context = context,
                    player = player,
                    gameManager = gameManager
                ) {
                    gameManager.checkIfHit(true)
                    gameManager.updateHearts(mainHearts)
                    player.fade()
                }

                if (tick % 2 == 0) {
                    StoneManager.spawn(
                        board = gameBoard.getBoard(),
                        context = context,
                        cols = gameBoard.getCols()
                    )
                }

                player.draw()

                delay(speed)

                speed = adjustSpeed(speed, tick)
            }
        }
    }
}
