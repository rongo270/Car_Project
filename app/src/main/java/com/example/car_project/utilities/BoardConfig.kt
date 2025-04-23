package com.example.car_project.utilities

import com.example.car_project.logic.gameSize.GameSize
import com.example.car_project.logic.gameSize.Large
import com.example.car_project.logic.gameSize.Medium
import com.example.car_project.logic.gameSize.Small

data class BoardConfig(
    var rows: Int,
    var cols: Int,
    var cellWidth: Int,
    var cellHeight: Int
) {
    companion object {
        fun from(size: GameSize): BoardConfig {
            return BoardConfig(
                rows = size.rows,
                cols = size.cols,
                cellWidth = size.cellWidth,
                cellHeight = size.cellHeight
            )
        }

        fun default(): BoardConfig = from(Small)
    }
}