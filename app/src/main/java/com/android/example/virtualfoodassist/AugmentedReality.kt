package com.android.example.virtualfoodassist

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import java.util.concurrent.CompletableFuture

public class AugmentedReality : AppCompatActivity() {

    lateinit var fragment: ArFragment
    lateinit var fitToScanImageView: ImageView
    lateinit var modelUri: Uri
    lateinit var renderableFuture: CompletableFuture<ModelRenderable>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this@AugmentedReality, "Welcome! Scan an item for a recipe!", Toast.LENGTH_LONG).show()

        fragment = supportFragmentManager.findFragmentById(R.id.arimage_fragment) as ArFragment
        fitToScanImageView = findViewById<ImageView>(R.id.fit_to_scan_img)

    }
}