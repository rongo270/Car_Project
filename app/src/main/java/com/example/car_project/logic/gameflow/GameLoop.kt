package com.example.car_project.logic.gameflow

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.car_project.logic.entities.Player.Player
import com.example.car_project.logic.helpers.Speed.SpeedChanger.adjustSpeed
import com.example.car_project.logic.managers.GameManager
import com.example.car_project.logic.entities.Stone.StoneManager
import com.example.car_project.utilities.Constants.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.example.car_project.GameBoard
import com.example.car_project.logic.helpers.Score.ScoreStorage
import com.example.car_project.logic.helpers.levels.LevelThree
import com.example.car_project.logic.helpers.levels.LevelTwo
import com.example.car_project.sound.SoundEffectManager
import com.example.car_project.ui.dialogs.Menu.EndMenu
import com.example.car_project.ui.dialogs.AddUser.NewUserUI
import kotlinx.coroutines.Job

object GameLoop {

    private var speed = 800L //Start speed

    private var gameJob: Job? = null //Pause and Resume handled

    private var isPaused = false

    private var scoreHandled = false //Check if game already ends


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

                if(scoreHandled){
                    pauseGameLoop()
                    EndMenu.show(context)
                }

                    tick++

                    gameManager.updateScore(Constants.POINT_FOR_SECOND)

                    StoneManager.moveAll(//Moves all stones down and return if hit was made
                        board = gameBoard.getBoard(),
                        context = context,
                        player = player,
                        gameManager = gameManager
                    )
                    { isCoin ->
                        if (!isCoin) {
                            gameManager.stoneHit()
                            gameManager.updateHearts(mainHearts)
                            player.fade()
                        } else {
                            gameManager.updateCoin(1)
                            gameManager.updateHearts(mainHearts)
                        }
                    }


                    if (gameManager.isGameOver && !scoreHandled) {//if game over
                        scoreHandled = true
                        //ScoreStorage.clearScores(context) Reset all Scores
                        pauseGameLoop()
                        val qualifies = //Check if top 10
                            ScoreStorage.qualifiesForTop10(context, gameManager.currentScore)
                        if (qualifies) { //If top 10 ask name and map
                            NewUserUI.show(context, gameManager.currentScore) { entry ->
                                ScoreStorage.addScore(context, entry)
                                EndMenu.show(context)
                            }
                        } else {
                            EndMenu.show(context)
                        }
                    }


                    if (tick % 2 == 0) {
                        StoneManager.spawn(
                            board = gameBoard.getBoard(),
                            context = context,
                            cols = gameBoard.getCols()
                        )
                    }

                    if (tick == Constants.LEVEL_TWO) {
                        LevelTwo.toLevelTwo(context, layout, soundEffect)
                    }
                    if (tick == Constants.LEVEL_THREE) {
                        LevelThree.toLevelThree(context, layout, soundEffect)
                    }

                    player.draw()//Needed

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
