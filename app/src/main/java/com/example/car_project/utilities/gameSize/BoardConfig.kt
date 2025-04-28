package com.example.car_project.utilities.gameSize

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


    }
}