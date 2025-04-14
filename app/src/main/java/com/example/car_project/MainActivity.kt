package com.example.car_project

import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import com.example.car_project.board.GameBoard
import com.example.car_project.logic.managers.GameManager
import com.example.car_project.logic.entities.Player
import com.google.android.material.textview.MaterialTextView
import androidx.lifecycle.lifecycleScope
import com.example.car_project.logic.managers.StoneManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var gameBoard: GameBoard

    private lateinit var gameManager: GameManager

    private lateinit var main_BTN_Left: AppCompatImageButton

    private lateinit var main_BTN_Right: AppCompatImageButton

    private lateinit var main_LBL_score: MaterialTextView

    private lateinit var main_IMG_hearts: Array<AppCompatImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        val player = Player()//create player
        val boardLayout = findViewById<GridLayout>(R.id.main_LAY_board)
        gameBoard = GameBoard(this, boardLayout)//start board
        gameBoard.initBoard(player)//build board

        findViews()
        gameManager = GameManager(main_IMG_hearts.size)//give hearts
        initViews()
        startGameLoop()//start game
    }

    private fun findViews() {
        main_BTN_Left = findViewById(R.id.main_BTN_Left)
        main_BTN_Right = findViewById(R.id.main_BTN_Right)
        main_LBL_score = findViewById(R.id.main_LBL_score)
        main_IMG_hearts = arrayOf(
            findViewById(R.id.main_IMG_heart1),
            findViewById(R.id.main_IMG_heart2),
            findViewById(R.id.main_IMG_heart3)
        )
    }

    private fun initViews() {
        main_BTN_Right.setOnClickListener {
            gameBoard.movePlayer(1, gameManager) {
                gameManager.updateHearts(main_IMG_hearts)//if collide
                gameBoard.getPlayer().fade()
            }
        }

        main_BTN_Left.setOnClickListener {
            gameBoard.movePlayer(-1, gameManager) {
                gameManager.updateHearts(main_IMG_hearts)//if collide
                gameBoard.getPlayer().fade()
            }
        }
    }

    private fun startGameLoop() {
        lifecycleScope.launch {
            var tick = 0
            while (true) {
                tick++

                StoneManager.moveAll(board = gameBoard.getBoard(), context = this@MainActivity,
                    player = gameBoard.getPlayer(), gameManager = gameManager) {//move stones
                    gameManager.checkIfHit(true)//if hit lose heart and fade
                    gameManager.updateHearts(main_IMG_hearts)
                    gameBoard.getPlayer().fade()
                }

                if (tick % 2 == 0) {//every 2 clock time
                    StoneManager.spawn(board = gameBoard.getBoard(), context = this@MainActivity,
                        cols = gameBoard.getCols())//spawn stone
                }

                delay(1000L)//w8 1 sec
            }
        }
    }
}
