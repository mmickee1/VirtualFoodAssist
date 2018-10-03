package com.android.example.virtualfoodassist

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView

public class Compass : AppCompatActivity(), SensorEventListener {

    private var image: ImageView? = null
    private var currentDegree = 0f
    private var mSensorManager: SensorManager? = null
    internal lateinit var degrees: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compass)

        Log.d("test", "At compass class")

        image = findViewById(R.id.compass) as ImageView
        degrees = findViewById(R.id.angle) as TextView
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

    }

    override fun onResume() {
        super.onResume()

        mSensorManager!!.registerListener(this, mSensorManager!!.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()

        mSensorManager!!.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        // get the angle around the z-axis rotated
        val degree = Math.round(event.values[0]).toFloat()

        degrees.text = "Heading: " + java.lang.Float.toString(degree) + " degrees"

        val rotationAnimation = RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f)
        rotationAnimation.duration = 210
        rotationAnimation.fillAfter = true
        image!!.startAnimation(rotationAnimation)
        currentDegree = -degree

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

}