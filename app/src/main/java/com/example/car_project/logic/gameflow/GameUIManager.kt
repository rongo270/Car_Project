package com.example.car_project.logic.gameflow

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import com.example.car_project.logic.entities.Player.Player
import com.example.car_project.logic.managers.GameManager
import com.example.car_project.sound.SoundEffectManager
import com.google.android.material.textview.MaterialTextView

object GameUIManager {


     fun initViews(
         context: Context,
         gameManager: GameManager,
         mainLeft: AppCompatImageButton,
         mainRight: AppCompatImageButton,
         mainHearts: Array<AppCompatImageView>,
         soundEffect: SoundEffectManager,
         mainScore: MaterialTextView,
         mainCoin: MaterialTextView,
         player: Player,
         sensorManger: SensorManager,
         tilt:Boolean
    )
     {
//__________________________________LEFT AND RIGHT___________________________________________\\
        mainRight.setOnClickListener {
            soundEffect.walkMedia(context)
            player.move(1, gameManager){
                isCoin ->
                if(!isCoin) {
                    gameManager.updateHearts(mainHearts)//if collide
                }
                else{
                    gameManager.updateCoin(1)
                    gameManager.updateHearts(mainHearts)
                }
            }
        }

        mainLeft.setOnClickListener {
            soundEffect.walkMedia(context)
            player.move(-1, gameManager){
                    isCoin ->
                if(!isCoin) {
                    gameManager.updateHearts(mainHearts)//if collide
                }
                else{
                    gameManager.updateCoin(1)
                    gameManager.updateHearts(mainHearts)
                }
            }
        }


//___________________________________ TILT MOVEMENT ____________________________________________\\
        if(tilt) {
            val accelerometer = sensorManger.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            if (accelerometer != null) {
                var lastMoveTime = 0L
                val tiltListener = object : SensorEventListener {
                    override fun onSensorChanged(event: SensorEvent?) {
                        val currentTime = System.currentTimeMillis()
                        if (currentTime - lastMoveTime < 300) return

                        val x = event?.values?.get(0) ?: return
                        if (x > 4) {
                            lastMoveTime = currentTime
                            player.move(-1, gameManager) {
                                gameManager.updateHearts(mainHearts)
                            }
                        } else if (x < -4) {
                            lastMoveTime = currentTime
                            player.move(1, gameManager) {
                                gameManager.updateHearts(mainHearts)
                            }
                        }
                    }

                    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
                }

                sensorManger.registerListener(
                    tiltListener,
                    accelerometer,
                    SensorManager.SENSOR_DELAY_GAME
                )
            }
        }
//___________________________________SCORE REFRESHER____________________________________________\\
        gameManager.setOnScoreChangedListener { updatedScore ->
           //mainScore.text = updatedScore.toString().padStart(3, '0')
            mainScore.text = "\uD83C\uDFC6 ${updatedScore.toString().padStart(3, '0')}"
        }
//___________________________________Coin REFRESHER______________________________________________\\
         gameManager.setOnCoinChangedListener { updatedCoin ->
             mainCoin.text = "💎 ${updatedCoin.toString().padStart(3, '0')}"
         }
    }
}