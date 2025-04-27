package com.example.car_project

import android.os.Bundle
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
import com.example.car_project.sound.MusicManager
import com.example.car_project.sound.SoundEffectManager
import com.example.car_project.utilities.BoardConfig

private const val i = 150

class MainActivity : AppCompatActivity() {
    private lateinit var gameBoard: GameBoard

    private lateinit var gameManager: GameManager

    private lateinit var mainLeft: AppCompatImageButton

    private lateinit var mainRight: AppCompatImageButton

    private lateinit var mainScore: MaterialTextView

    private lateinit var mainHearts: Array<AppCompatImageView>

    private lateinit var player : Player

    private lateinit var musicManager: MusicManager

    private lateinit var soundEffect: SoundEffectManager

    private var speed = 800L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        player = Player()//create player

        val config = BoardConfig.default()

        val boardLayout = findViewById<GridLayout>(R.id.main_LAY_board)
        gameBoard = GameBoard(this, boardLayout,config)//start board
        gameBoard.initBoard(player)//build board

        findViews()

        gameManager = GameManager(mainHearts.size)//give hearts

        initViews()

        musicManager = MusicManager()
        soundEffect = SoundEffectManager()
        musicManager.startMusic(this)

        GameLoop.startGameLoop(lifecycleScope = lifecycleScope,
            this,player,gameBoard,gameManager,mainHearts, speed)
    }


    private fun findViews() {
        mainLeft = findViewById(R.id.main_BTN_Left)
        mainRight = findViewById(R.id.main_BTN_Right)
        mainScore = findViewById(R.id.main_LBL_score)
        mainHearts = arrayOf(
            findViewById(R.id.main_IMG_heart1),
            findViewById(R.id.main_IMG_heart2),
            findViewById(R.id.main_IMG_heart3)
        )
    }

    private fun initViews() {
        mainRight.setOnClickListener {
            soundEffect.walkMedia(this)
            gameManager.movePlayer(1, gameManager,gameBoard.getPlayer()) {
                gameManager.updateHearts(mainHearts)//if collide
            }
        }

        mainLeft.setOnClickListener {
            soundEffect.walkMedia(this)
            gameManager.movePlayer(-1, gameManager,gameBoard.getPlayer()) {
                gameManager.updateHearts(mainHearts)//if collide
            }
        }

        gameManager.setOnScoreChangedListener { updatedScore ->
            mainScore.text = updatedScore.toString().padStart(3, '0')
        }
    }
}
