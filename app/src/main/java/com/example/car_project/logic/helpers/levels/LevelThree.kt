package com.example.car_project.logic.helpers.levels

import android.content.Context
import android.view.View
import com.example.car_project.R
import com.example.car_project.logic.entities.Stone.StoneManager
import com.example.car_project.sound.SoundEffectManager

object LevelThree {

    fun toLevelThree(context: Context, layout: View, soundEffect: SoundEffectManager){
        StoneManager.levelGoesThree()
        layout.setBackgroundResource(R.drawable.the_end)
        soundEffect.netherMedia(context)
    }

}