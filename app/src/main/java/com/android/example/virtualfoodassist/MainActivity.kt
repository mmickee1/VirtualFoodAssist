package com.android.example.virtualfoodassist

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_camera.setOnClickListener{
            AugmentedReality()
            Log.d("clicked", "Camera")
        }

        txt_map.setOnClickListener{
            Log.d("clicked", "Map")
        }

        txt_history.setOnClickListener{
            Log.d("clicked", "History")
        }

        txt_info.setOnClickListener{
            Log.d("clicked", "Info")
        }
    }
}
