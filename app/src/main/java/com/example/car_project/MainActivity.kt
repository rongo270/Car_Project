package com.example.car_project

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import com.example.car_project.logic.managers.GameManager
import com.example.car_project.logic.entities.Player
import com.google.android.material.textview.MaterialTextView
import androidx.lifecycle.lifecycleScope
import com.example.car_project.logic.gameflow.GameLoop
import com.example.car_project.logic.gameflow.GameUIManager
import com.example.car_project.sound.MusicManager
import com.example.car_project.sound.SoundEffectManager
import com.example.car_project.ui.dialogs.SizeSelect
import com.example.car_project.utilities.gameSize.BoardConfig
import com.example.car_project.utilities.gameSize.GameSize


class MainActivity : AppCompatActivity() {

    private lateinit var gameBoard: GameBoard

    private lateinit var gameManager: GameManager

    private lateinit var mainLeft: AppCompatImageButton

    private lateinit var mainRight: AppCompatImageButton

    private lateinit var mainScore: MaterialTextView

    private lateinit var mainCoin: MaterialTextView

    private lateinit var mainHearts: Array<AppCompatImageView>

    private lateinit var player : Player

    private lateinit var musicManager: MusicManager

    private lateinit var soundEffect: SoundEffectManager

    private lateinit var sensorManager: SensorManager




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        findViews()
        musicManager = MusicManager()
        musicManager.startMusic(this)

        SizeSelect.showSizeSelectionDialog(this) { selectedSize, useTilt, useArrows ->
            setupGame(selectedSize, useTilt, useArrows)
        }

    }

private fun setupGame(selectedSize: GameSize, useTilt: Boolean, useArrows: Boolean) { //i may move it to another object, you think its good?
        //create player
        player = Player()

        //build and start board
        val boardLayout = findViewById<GridLayout>(R.id.main_LAY_board)
        val config = BoardConfig.from(selectedSize)
        gameBoard = GameBoard(this, boardLayout,config)
        gameBoard.initBoard(player)

        //give hearts
        gameManager = GameManager(mainHearts.size)

        //sound manage
        soundEffect = SoundEffectManager()

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if(!useArrows){
            mainLeft.visibility = View.GONE
            mainRight.visibility = View.GONE
        }
        //game flow
        GameUIManager.initViews(this,gameManager,mainLeft,mainRight, mainHearts,soundEffect,mainScore,mainCoin,player,sensorManager,useTilt)

        val rootLayout = findViewById<View>(R.id.main)
        GameLoop.startGameLoop(lifecycleScope = lifecycleScope, this,player,gameBoard,gameManager,mainHearts,rootLayout,soundEffect)
    }


    private fun findViews() {
        mainLeft = findViewById(R.id.main_BTN_Left)
        mainRight = findViewById(R.id.main_BTN_Right)
        mainScore = findViewById(R.id.main_LBL_score)
        mainCoin = findViewById(R.id.main_LBL_diamonds)
        mainHearts = arrayOf(
            findViewById(R.id.main_IMG_heart1),
            findViewById(R.id.main_IMG_heart2),
            findViewById(R.id.main_IMG_heart3)
        )
    }
}
