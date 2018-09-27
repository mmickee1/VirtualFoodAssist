package com.android.example.virtualfoodassist

import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.ar.core.AugmentedImage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_camera.setOnClickListener {
            Log.d("clicked", "Camera")
            val intent = Intent(this, AugmentedReality::class.java)
            startActivity(intent)
        }

        txt_info.setOnClickListener {
            Log.d("clicked", "info")
            supportFragmentManager.beginTransaction().replace(R.id.main_container_child, Info()).commit()
        }

        txt_map.setOnClickListener {
            Log.d("clicked", "Map")
            //supportFragmentManager.beginTransaction().replace(R.id.main_container_child, Location()).commit()
            //val intent = Intent(this, Location::class.java)
            //startActivity(intent)
        }

        txt_history.setOnClickListener {
            Log.d("clicked", "History")
        }

    }
}
