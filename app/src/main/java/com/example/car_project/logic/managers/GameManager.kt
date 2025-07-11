package com.example.car_project.logic.managers

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.example.car_project.logic.entities.Player.Player
import com.example.car_project.logic.entities.Stone.Stone

class GameManager(private val lifeCount: Int = 5) {

    private var score: Int = 0
        private set

    val currentScore: Int
        get() = score

    private var coin: Int = 0
        private set

    private var onScoreChanged: ((Int) -> Unit)? = null

    private var obstacleHit: Int = 2 //Start with -2 hearts (Armor)
        private set

    private var coinPick: ((Int) -> Unit)? = null
        private set

     val isGameOver: Boolean
        get() = obstacleHit >= lifeCount

    //Functions
    //___________________________________________________________________________________________\\


    //______________________________SCORE________________________________________________________\\
    fun setOnScoreChangedListener(listener: (Int) -> Unit) {
        onScoreChanged = listener
    }

    fun updateScore(number: Int) {
        score += number
        onScoreChanged?.invoke(score)
    }

    //________________________________HIT dealer___________________________________________________\\
    fun stoneHit() {
            obstacleHit++
    }

    fun didCollide(stone: Stone, player: Player): Boolean {
        return stone.getNextRow() == player.getRow() &&
                stone.col == player.getCol()
    }


    //___________________________HEART CHANGER______________________________________________________\\

    fun updateHearts(hearts: Array<AppCompatImageView>) {
        for (i in hearts.indices) {
            hearts[i].visibility = if (i < obstacleHit) View.INVISIBLE else View.VISIBLE
        }

    }

    //_____________________________Coin Update_________________________________________________\\

    fun setOnCoinChangedListener(listener: (Int) -> Unit) {
        coinPick = listener
    }

    fun updateCoin(number: Int) {
        coin += number

        while (coin >= 10 && obstacleHit > 0) {
            coin -= 10
            obstacleHit--
        }

        coinPick?.invoke(coin)
    }

    //______________________________Game-Over___________________________________________________\\
     fun resetLives(hearts: Array<AppCompatImageView>) {
        hearts[0].visibility = View.INVISIBLE //Hearts 0-1 are armor
        hearts[1].visibility = View.INVISIBLE
        obstacleHit = 2
    }

    fun refresh() {
    }
}
