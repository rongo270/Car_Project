package com.example.car_project.board

import android.content.Context
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import com.example.car_project.logic.GameManager
import com.example.car_project.logic.entities.Player
import com.example.car_project.logic.entities.Stone

class GameBoard(
    private val context: Context,
    private val gridLayout: GridLayout,
    private val rows: Int = 6,
    private val cols: Int = 3
) {
    private val board: Array<Array<ImageView>> = Array(rows) {
        Array(cols) { ImageView(context) }
    }

    private lateinit var player: Player
    private val stones = mutableListOf<Stone>()

    fun initBoard(player: Player) {
        this.player = player
        gridLayout.removeAllViews()
        gridLayout.rowCount = rows
        gridLayout.columnCount = cols

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val imageView = ImageView(context).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = 100
                        height = 100
                        setMargins(8, 8, 8, 8)
                    }
                    id = View.generateViewId()
                }
                board[row][col] = imageView
                gridLayout.addView(imageView)
            }
        }

        player.init(board, context)
    }

    fun movePlayer(deltaCol: Int) {
        player.move(deltaCol)
    }

    fun spawnStone() {
        val col = (0 until cols).random()
        val stone = Stone(0, col)
        stones.add(stone)
        stone.draw(board, context)
    }

    fun moveStones(gameManager: GameManager, onHit: () -> Unit) {
        val iterator = stones.iterator()
        while (iterator.hasNext()) {
            val stone = iterator.next()

            if (gameManager.didCollide(stone, player)) {
                onHit()
                stone.clear(board)
                iterator.remove()
            } else if (!stone.moveDown(board, context)) {
                iterator.remove()
            }
        }
    }

    fun getPlayer(): Player = player
}
