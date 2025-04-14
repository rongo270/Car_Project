package com.example.car_project

import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import com.example.car_project.board.GameBoard
import com.example.car_project.logic.GameManager
import com.example.car_project.logic.entities.Player
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    private lateinit var gameBoard: GameBoard



   private lateinit var main_BTN_Left : AppCompatImageButton

   private lateinit var main_BTN_Right : AppCompatImageButton

   private lateinit var main_LBL_score : MaterialTextView

   private lateinit var main_IMG_hearts: Array<AppCompatImageView>

   private lateinit var gameManager: GameManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        // create player
        val player = Player()

        // create board and init with player
        val boardLayout = findViewById<GridLayout>(R.id.main_LAY_board)
        gameBoard = GameBoard(this, boardLayout)
        gameBoard.initBoard(player) // pass the player

        findViews()
        gameManager = GameManager(main_IMG_hearts.size)
        initViews()
    }

    private fun findViews(){
        main_BTN_Left = findViewById(R.id.main_BTN_Left)
        main_BTN_Right  = findViewById(R.id.main_BTN_Right)
        main_LBL_score = findViewById(R.id.main_LBL_score)
        main_IMG_hearts = arrayOf(
            findViewById(R.id.main_IMG_heart1),
            findViewById(R.id.main_IMG_heart2),
            findViewById(R.id.main_IMG_heart3)

        )

    }
    private fun initViews(){
        //main_LBL_score.text = gameManager.score.toString()
        main_BTN_Right.setOnClickListener {
            gameBoard.movePlayer(1)
        }
        main_BTN_Left.setOnClickListener {
            gameBoard.movePlayer(-1)
        }
    }
}