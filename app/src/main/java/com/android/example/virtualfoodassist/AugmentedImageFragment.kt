package com.android.example.virtualfoodassist

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.ux.ArFragment

class AugmentedImageFragment: ArFragment(){

    private val SAMPLE_IMAGE_DATABASE ="sample_database.imgdb"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = super.onCreateView(inflater, container, savedInstanceState)
        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
        //arSceneView.planeRenderer.isEnabled = false
        return view
    }

    override fun getSessionConfiguration(session: Session?): Config {
        val config = super.getSessionConfiguration(session)
        setupAugmentedImageDatabase(config,session)
        return config
    }

    private fun setupAugmentedImageDatabase(config: Config, session: Session?){
        val augmentedImageDb: AugmentedImageDatabase
        val assetManager = context!!.assets

        // Pasta
        val pasta1 = assetManager.open("pasta/pasta1.jpg")
        val augmentedImageBitmapPasta1 = BitmapFactory.decodeStream(pasta1)
        val pasta2 = assetManager.open("pasta/pasta2.jpg")
        val augmentedImageBitmapPasta2 = BitmapFactory.decodeStream(pasta2)

        augmentedImageDb = AugmentedImageDatabase(session)

        augmentedImageDb.addImage("pasta1", augmentedImageBitmapPasta1)
        augmentedImageDb.addImage("pasta2", augmentedImageBitmapPasta2)

        config.augmentedImageDatabase = augmentedImageDb
    }
}