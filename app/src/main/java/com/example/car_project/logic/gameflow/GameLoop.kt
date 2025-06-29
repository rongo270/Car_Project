package com.example.car_project.logic.gameflow

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.car_project.logic.entities.Player
import com.example.car_project.logic.helpers.SpeedChanger.adjustSpeed
import com.example.car_project.logic.managers.GameManager
import com.example.car_project.logic.managers.StoneManager
import com.example.car_project.utilities.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.example.car_project.GameBoard
import com.example.car_project.logic.levels.LevelThree
import com.example.car_project.logic.levels.LevelTwo
import com.example.car_project.sound.SoundEffectManager
import kotlinx.coroutines.Job

object GameLoop {

    private var speed = 800L

    private var gameJob: Job? = null

    private var isPaused = false

    fun startGameLoop(
        lifecycleScope: LifecycleCoroutineScope,
        context: Context,
        player: Player,
        gameBoard: GameBoard,
        gameManager: GameManager,
        mainHearts: Array<AppCompatImageView>,
        layout: View,
        soundEffect: SoundEffectManager,
    ) {
        gameJob = lifecycleScope.launch {

            var tick = 0

            while (true) {
                if (isPaused) {
                    delay(100)
                    continue
                }


                tick++

                    gameManager.updateScore(Constants.POINT_FOR_SECOND)

                    StoneManager.moveAll(
                        board = gameBoard.getBoard(),
                        context = context,
                        player = player,
                        gameManager = gameManager
                    )
                    { isCoin ->
                        if (!isCoin) {
                            gameManager.checkIfHit(true)
                            gameManager.updateHearts(mainHearts)
                            player.fade()
                        } else {
                            gameManager.updateCoin(1)
                            gameManager.updateHearts(mainHearts)
                        }
                    }


                    if (tick % 2 == 0) {
                        StoneManager.spawn(
                            board = gameBoard.getBoard(),
                            context = context,
                            cols = gameBoard.getCols()
                        )
                    }

                    if (tick == Constants.LEVEL_TWO) {//Levels check, may need change for better complexities
                        LevelTwo.toLevelTwo(context, layout, soundEffect)
                    }
                    if (tick == Constants.LEVEL_THREE) {
                        LevelThree.toLevelThree(context, layout, soundEffect)
                    }

                    player.draw()//needed

                    delay(speed)

                    speed = adjustSpeed(speed, tick)
                }
            }
        }
        fun pauseGameLoop() {
            isPaused = true
        }
        fun resume() {
            isPaused = false
        }
    }
