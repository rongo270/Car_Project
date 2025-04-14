package com.example.car_project.logic.managers

import android.content.Context
import android.widget.ImageView
import com.example.car_project.logic.managers.GameManager
import com.example.car_project.logic.entities.Player
import com.example.car_project.logic.entities.Stone

object StoneManager {
    private val stones = mutableListOf<Stone>()

    fun spawn(board: Array<Array<ImageView>>, context: Context, cols: Int) {
        val col = (0 until cols).random()
        val stone = Stone(0, col)
        stones.add(stone)
        stone.draw(board, context)
    }

    fun moveAll(board: Array<Array<ImageView>>, context: Context, player: Player,
                gameManager: GameManager, onHit: () -> Unit) {
        val iterator = stones.iterator()
        while (iterator.hasNext()) {//goes over all array
            val stone = iterator.next()
            val alive = stone.update(board, context, player, gameManager, onHit)//update the stone
            if (!alive) {//if collide "kill" stone and remove
                iterator.remove()
            }
        }
    }

    fun getStones(): List<Stone> = stones

    fun reset() {
        stones.clear()
    }
}
