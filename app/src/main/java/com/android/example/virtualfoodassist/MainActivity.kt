package com.android.example.virtualfoodassist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.app_name)

        txt_camera.setOnClickListener {
            Log.d("clicked", "Camera")
            setContentView(R.layout.splash)
            // Opens a splash screen for 2,5 seconds before opening a camera view
            val background = object : Thread() {
                override fun run() {
                    try {
                        Thread.sleep(2500)

                        val intent = Intent(baseContext, AugmentedReality::class.java)
                        startActivity(intent)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            background.start()
        }

        txt_info.setOnClickListener {
            Log.d("clicked", "info")
            supportFragmentManager.beginTransaction().replace(R.id.main_container_child, Info()).addToBackStack(null).commit()
            //  .add(detailFragment, "detail")... better back navigation?
        }

        txt_map.setOnClickListener {
            Log.d("clicked", "Map")
            //supportFragmentManager.beginTransaction().replace(R.id.main_container_child, LocationGoogle()).addToBackStack(null).commit()
            val intent = Intent(baseContext, LocationGoogle::class.java)
            startActivity(intent)
        }

        txt_history.setOnClickListener {
            Log.d("clicked", "History")
            supportFragmentManager.beginTransaction().replace(R.id.main_container_child, History()).addToBackStack(null).commit()
        }
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStackImmediate()
    }

    override fun onResume() {
        super.onResume()
        setTitle(R.string.app_name)
    }
}
