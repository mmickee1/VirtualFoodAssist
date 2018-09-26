package com.android.example.virtualfoodassist

import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //for back button but doesnt work like i want, trying something else atm.
        //supportActionBar?.setDisplayShowHomeEnabled(true)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txt_camera.setOnClickListener {
            Log.d("clicked", "Camera")
            //placeholder.visibility = View.VISIBLE
            //ArHelper().setUp()
            //supportFragmentManager.beginTransaction().replace(R.id.placeholder, ArHelper.AugmentedImageFragmentREAL()).commit()
            val intent = Intent(this, AugmentedReality::class.java)
            startActivity(intent)
        }

        txt_info.setOnClickListener {
            Log.d("clicked", "info")
            //TODO: this is working but having back button to go back to mainscreen is not functioning atm. fix it.
            supportFragmentManager.beginTransaction().replace(R.id.main_container_child, Info()).commit()
        }

        txt_map.setOnClickListener{
            Log.d("clicked", "Map")
        }

        txt_history.setOnClickListener{
            Log.d("clicked", "History")
        }

    }

    fun augmentedReality(){
        AugmentedReality()
        Log.d("access", "accessing another class")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id: Int = item!!.itemId

        if (id == android.R.id.home) {
            //setContentView(R.layout.activity_main)
            //needs onResume func and it didnt work as expected.. trying smth else
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
