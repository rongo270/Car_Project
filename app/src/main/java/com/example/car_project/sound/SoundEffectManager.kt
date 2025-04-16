package com.example.car_project.sound

import android.content.Context
import android.media.MediaPlayer
import com.example.car_project.R

class SoundEffectManager {

    private lateinit var hitPlayer: MediaPlayer

    private lateinit var walkPlayer: MediaPlayer

    fun hitMedia(context: Context){
        hitPlayer = MediaPlayer.create(context, R.raw.ouch_sound)
        hitPlayer.setVolume(0.1f, 0.1f)
        hitPlayer.setOnCompletionListener { it.release() }
        hitPlayer.start()
    }

    fun walkMedia(context: Context){
        walkPlayer = MediaPlayer.create(context, R.raw.walk)
        walkPlayer.setVolume(0.02f, 0.02f)
        walkPlayer.setOnCompletionListener { it.release() }
        walkPlayer.start()
    }


}