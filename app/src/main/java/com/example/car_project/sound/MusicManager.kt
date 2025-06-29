package com.example.car_project.sound

import android.content.Context
import android.media.MediaPlayer
import com.example.car_project.R

class MusicManager {


    private lateinit var mediaPlayer: MediaPlayer


    fun startMusic(context: Context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.minecraft_theme)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    fun pauseMusic() {
        mediaPlayer.pause()
    }

    fun resumeMusic() {
        mediaPlayer.start()
    }

}