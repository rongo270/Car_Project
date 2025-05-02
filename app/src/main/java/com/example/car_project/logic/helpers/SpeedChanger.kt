package com.example.car_project.logic.helpers

import com.example.car_project.utilities.Constants

object SpeedChanger {
    fun adjustSpeed(currentSpeed: Long, tick: Int):Long{
         when {//Adjust speed base on current speed (medium/hard)
            currentSpeed > Constants.MAX_SPEED && tick % 10 == 0 -> {
                return currentSpeed - Constants.SPEED_INCREASE
            }
            currentSpeed > Constants.HARD_SPEED && tick % 5 == 0 -> {
                return currentSpeed - Constants.SPEED_INCREASE
            }
            currentSpeed > Constants.MEDIUM_SPEED -> {
                return currentSpeed - Constants.SPEED_INCREASE
            }
        }
        return currentSpeed
    }

}