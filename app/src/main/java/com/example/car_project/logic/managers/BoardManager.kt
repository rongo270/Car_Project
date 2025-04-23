package com.example.car_project.logic.managers

import com.example.car_project.utilities.BoardConfig


class BoardManager(
    private val config: BoardConfig,
    private val gameManager: GameManager
) {

    fun applySettings(
        rows: Int,
        cols: Int,
        width: Int,
        height: Int,
        speed: Long
    ) {
        config.rows = rows
        config.cols = cols
        config.cellWidth = width
        config.cellHeight = height

        gameManager.refresh()
    }
}
