package com.example.car_project.logic.managers

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.example.car_project.logic.entities.Player
import com.example.car_project.logic.entities.Stone

class GameManager(private val lifeCount: Int = 3) {

    private var score: Int = 0
        private set

    private var coin: Int = 0
        private set

    private var onScoreChanged: ((Int) -> Unit)? = null

    private var obstacleHit: Int = 0
        private set

    private var coinPick: ((Int) -> Unit)? = null
        private set

    private val isGameOver: Boolean
        get() = obstacleHit >= lifeCount

                                 //functions
    //___________________________________________________________________________________________\\


    //______________________________SCORE________________________________________________________\\
    fun setOnScoreChangedListener(listener: (Int) -> Unit) {
        onScoreChanged = listener
    }
    fun updateScore(number: Int){
        score += number
        onScoreChanged?.invoke(score)
    }

//________________________________HIT dealer___________________________________________________\\
    fun checkIfHit(wasHit: Boolean) {
        if (wasHit) {
            obstacleHit++
        }
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

        if (isGameOver) {
            resetLives()
            for (heart in hearts) {
                heart.visibility = View.VISIBLE
            }
        }
    }

    //_____________________________Coin Update_________________________________________________\\

    fun setOnCoinChangedListener(listener: (Int) -> Unit) {
        coinPick = listener
    }
    fun updateCoin(number: Int){
        coin += number
        coinPick?.invoke(coin)
    }

    //______________________________Game-Over___________________________________________________\\
    private fun resetLives() {
        obstacleHit = 0
    }

    fun refresh(){
    }
}
