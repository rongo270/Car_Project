package com.example.car_project.logic.gameflow

import android.content.Context
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import com.example.car_project.GameBoard
import com.example.car_project.logic.managers.GameManager
import com.example.car_project.sound.SoundEffectManager
import com.google.android.material.textview.MaterialTextView

object GameUIManager {


     fun initViews(
        context: Context,
        gameBoard: GameBoard,
        gameManager: GameManager,
        mainLeft: AppCompatImageButton,
        mainRight: AppCompatImageButton,
        mainHearts: Array<AppCompatImageView>,
        soundEffect: SoundEffectManager,
        mainScore: MaterialTextView


    ) {
        mainRight.setOnClickListener {
            soundEffect.walkMedia(context)
            gameManager.movePlayer(1, gameManager,gameBoard.getPlayer()) {
                gameManager.updateHearts(mainHearts)//if collide
            }
        }

        mainLeft.setOnClickListener {
            soundEffect.walkMedia(context)
            gameManager.movePlayer(-1, gameManager,gameBoard.getPlayer()) {
                gameManager.updateHearts(mainHearts)//if collide
            }
        }

        gameManager.setOnScoreChangedListener { updatedScore ->
            mainScore.text = updatedScore.toString().padStart(3, '0')
        }
    }
}