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
        val pasta3 = assetManager.open("pasta/pasta3.jpg")
        val augmentedImageBitmapPasta3 = BitmapFactory.decodeStream(pasta3)
        val pasta4 = assetManager.open("pasta/pasta4.jpg")
        val augmentedImageBitmapPasta4 = BitmapFactory.decodeStream(pasta4)
        val pasta5 = assetManager.open("pasta/pasta5.jpg")
        val augmentedImageBitmapPasta5 = BitmapFactory.decodeStream(pasta5)
        val pasta6 = assetManager.open("pasta/pasta6.jpg")
        val augmentedImageBitmapPasta6 = BitmapFactory.decodeStream(pasta6)
        val pasta7 = assetManager.open("pasta/pasta7.jpg")
        val augmentedImageBitmapPasta7 = BitmapFactory.decodeStream(pasta7)
        val pasta8 = assetManager.open("pasta/pasta8.jpg")
        val augmentedImageBitmapPasta8 = BitmapFactory.decodeStream(pasta8)
        val pasta9 = assetManager.open("pasta/pasta9.jpg")
        val augmentedImageBitmapPasta9 = BitmapFactory.decodeStream(pasta9)
        val pasta10 = assetManager.open("pasta/pasta10.jpg")
        val augmentedImageBitmapPasta10 = BitmapFactory.decodeStream(pasta10)
        val pasta11 = assetManager.open("pasta/pasta11.jpg")
        val augmentedImageBitmapPasta11 = BitmapFactory.decodeStream(pasta11)
        val pasta12 = assetManager.open("pasta/pasta12.jpg")
        val augmentedImageBitmapPasta12 = BitmapFactory.decodeStream(pasta12)
        val pasta13 = assetManager.open("pasta/pasta12.jpg")
        val augmentedImageBitmapPasta13 = BitmapFactory.decodeStream(pasta13)


        // Pizza


        augmentedImageDb = AugmentedImageDatabase(session)

        augmentedImageDb.addImage("pasta1", augmentedImageBitmapPasta1)
        augmentedImageDb.addImage("pasta2", augmentedImageBitmapPasta2)
        augmentedImageDb.addImage("pasta3", augmentedImageBitmapPasta3)
        augmentedImageDb.addImage("pasta4", augmentedImageBitmapPasta4)
        augmentedImageDb.addImage("pasta5", augmentedImageBitmapPasta5)
        augmentedImageDb.addImage("pasta6", augmentedImageBitmapPasta6)
        augmentedImageDb.addImage("pasta7", augmentedImageBitmapPasta7)
        augmentedImageDb.addImage("pasta8", augmentedImageBitmapPasta8)
        augmentedImageDb.addImage("pasta9", augmentedImageBitmapPasta9)
        augmentedImageDb.addImage("pasta10", augmentedImageBitmapPasta10)
        augmentedImageDb.addImage("pasta11", augmentedImageBitmapPasta11)
        augmentedImageDb.addImage("pasta12", augmentedImageBitmapPasta12)
        augmentedImageDb.addImage("pasta13", augmentedImageBitmapPasta13)

        config.augmentedImageDatabase = augmentedImageDb
    }
}