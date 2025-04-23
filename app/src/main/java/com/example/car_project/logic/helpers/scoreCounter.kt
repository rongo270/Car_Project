package com.example.car_project.logic.helpers

import com.example.car_project.utilities.Constants

object scoreCounter {
    fun addScoreCount(score: Int, tick: Int):Int{
        if(tick % 10 == 0){
            return score + Constants.POINT_FOR_SECOND
        }
        return score
    }
}