package com.example.car_project.logic.managers

import android.content.Context
import android.widget.ImageView
import com.example.car_project.logic.entities.Player
import com.example.car_project.logic.entities.Stone
import com.example.car_project.utilities.Constants

object StoneManager {
    private val stones = mutableListOf<Stone>()
    private var currentLevel = 1


    fun spawn(board: Array<Array<ImageView>>, context: Context, cols: Int) {
        val col = (0 until cols).random()
        val roll = (1..100).random()
        val isCoin = roll <= Constants.COIN_CHANCE
        val stone = Stone(0, col,currentLevel,isCoin)
        stones.add(stone)
        stone.drawStone(board, context)
    }

    fun moveAll(board: Array<Array<ImageView>>, context: Context, player: Player,
                gameManager: GameManager, onHit: (isCoin:Boolean) -> Unit) {
        val iterator = stones.iterator()
        while (iterator.hasNext()) {//goes over all array
            val stone = iterator.next()
            val alive = stone.update(board, context, player, gameManager){
                onHit(stone.isCoin)
            }//update the stone
            if (!alive) {//if collide "kill" stone and remove
                iterator.remove()
            }
        }
    }

    fun getStones(): List<Stone> = stones

    //_________________________________LEVELS___________________________________________________\\

    fun levelGoesTwo(){
        currentLevel = 2
    }
    fun levelGoesThree(){
        currentLevel = 3
    }

//    fun reset() {
//        stones.clear() will be next h.m
//    }
}
