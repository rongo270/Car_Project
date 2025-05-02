package com.example.car_project.sound

import android.content.Context
import android.media.MediaPlayer
import com.example.car_project.R

class SoundEffectManager {

    private lateinit var hitPlayer: MediaPlayer

    private lateinit var walkPlayer: MediaPlayer

    private lateinit var netherPortal: MediaPlayer

    fun hitMedia(context: Context){
        hitPlayer = MediaPlayer.create(context, R.raw.ouch_sound)
        hitPlayer.setVolume(0.1f, 0.1f)
        hitPlayer.setOnCompletionListener { it.release() }
        hitPlayer.start()
    }

    fun walkMedia(context: Context){
        walkPlayer = MediaPlayer.create(context, R.raw.walk)
        walkPlayer.setVolume(0.08f, 0.08f)
        walkPlayer.setOnCompletionListener { it.release() }
        walkPlayer.start()
    }

    fun netherMedia(context: Context){
        netherPortal = MediaPlayer.create(context, R.raw.thenetherprotol)
        netherPortal.setVolume(0.1f, 0.1f)
        netherPortal.setOnCompletionListener { it.release() }
        netherPortal.start()
    }

}