package com.example.car_project.sound

import android.content.Context
import android.media.MediaPlayer
import com.example.car_project.R

class Sound_Media {
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var hitPlayer: MediaPlayer

    private lateinit var walkPlayer: MediaPlayer

    fun startMusic(context: Context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.minecraft_theme)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    fun hit_Media(context: Context){
        hitPlayer = MediaPlayer.create(context, R.raw.ouch_sound)
        hitPlayer.setOnCompletionListener { it.release() }
        hitPlayer.start()
    }

    fun walk_Media(context: Context){
        walkPlayer = MediaPlayer.create(context, R.raw.walk)
        walkPlayer.setOnCompletionListener { it.release() }
        walkPlayer.start()
    }



}