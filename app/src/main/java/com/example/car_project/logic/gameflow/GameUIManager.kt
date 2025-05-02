package com.example.car_project.logic.gameflow

import android.content.Context
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import com.example.car_project.logic.entities.Player
import com.example.car_project.logic.managers.GameManager
import com.example.car_project.sound.SoundEffectManager
import com.google.android.material.textview.MaterialTextView

object GameUIManager {


     fun initViews(
        context: Context,
        gameManager: GameManager,
        mainLeft: AppCompatImageButton,
        mainRight: AppCompatImageButton,
        mainHearts: Array<AppCompatImageView>,
        soundEffect: SoundEffectManager,
        mainScore: MaterialTextView,
        player: Player

    )
     {
//__________________________________LEFT AND RIGHT___________________________________________\\
        mainRight.setOnClickListener {
            soundEffect.walkMedia(context)
            player.move(1, gameManager){
                gameManager.updateHearts(mainHearts)//if collide
            }
        }

        mainLeft.setOnClickListener {
            soundEffect.walkMedia(context)
            player.move(-1, gameManager){
                gameManager.updateHearts(mainHearts)//if collide
            }
        }
//___________________________________SCORE REFRESHER____________________________________________\
        gameManager.setOnScoreChangedListener { updatedScore ->
            mainScore.text = updatedScore.toString().padStart(3, '0')
        }
    }
}