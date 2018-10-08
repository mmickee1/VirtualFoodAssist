package com.android.example.virtualfoodassist

import android.graphics.BitmapFactory
import android.os.Bundle
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
        arSceneView.planeRenderer.isEnabled = false
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
        val pasta13 = assetManager.open("pasta/pasta13.jpg")
        val augmentedImageBitmapPasta13 = BitmapFactory.decodeStream(pasta13)
        val pasta14 = assetManager.open("pasta/pasta14.jpg")
        val augmentedImageBitmapPasta14 = BitmapFactory.decodeStream(pasta14)


        // Pizza
        val pizza1 = assetManager.open("pizza/pizza1.jpg")
        val augmentedImageBitmapPizza1 = BitmapFactory.decodeStream(pizza1)
        val pizza2 = assetManager.open("pizza/pizza2.jpg")
        val augmentedImageBitmapPizza2 = BitmapFactory.decodeStream(pizza2)
        val pizza3 = assetManager.open("pizza/pizza3.jpg")
        val augmentedImageBitmapPizza3 = BitmapFactory.decodeStream(pizza3)
        val pizza4 = assetManager.open("pizza/pizza4.jpg")
        val augmentedImageBitmapPizza4 = BitmapFactory.decodeStream(pizza4)
        val pizza5 = assetManager.open("pizza/pizza5.jpg")
        val augmentedImageBitmapPizza5 = BitmapFactory.decodeStream(pizza5)
        val pizza6 = assetManager.open("pizza/pizza6.jpg")
        val augmentedImageBitmapPizza6 = BitmapFactory.decodeStream(pizza6)
        val pizza7 = assetManager.open("pizza/pizza7.jpg")
        val augmentedImageBitmapPizza7 = BitmapFactory.decodeStream(pizza7)
        val pizza8 = assetManager.open("pizza/pizza8.jpg")
        val augmentedImageBitmapPizza8 = BitmapFactory.decodeStream(pizza8)
        val pizza9 = assetManager.open("pizza/pizza9.jpg")
        val augmentedImageBitmapPizza9 = BitmapFactory.decodeStream(pizza9)
        val pizza10 = assetManager.open("pizza/pizza10.jpg")
        val augmentedImageBitmapPizza10 = BitmapFactory.decodeStream(pizza10)


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
        augmentedImageDb.addImage("pasta14", augmentedImageBitmapPasta14)

        augmentedImageDb.addImage("pizza1", augmentedImageBitmapPizza1)
        augmentedImageDb.addImage("pizza2", augmentedImageBitmapPizza2)
        augmentedImageDb.addImage("pizza3", augmentedImageBitmapPizza3)
        augmentedImageDb.addImage("pizza4", augmentedImageBitmapPizza4)
        augmentedImageDb.addImage("pizza5", augmentedImageBitmapPizza5)
        augmentedImageDb.addImage("pizza6", augmentedImageBitmapPizza6)
        augmentedImageDb.addImage("pizza7", augmentedImageBitmapPizza7)
        augmentedImageDb.addImage("pizza8", augmentedImageBitmapPizza8)
        augmentedImageDb.addImage("pizza9", augmentedImageBitmapPizza9)
        augmentedImageDb.addImage("pizza10", augmentedImageBitmapPizza10)

        config.augmentedImageDatabase = augmentedImageDb
    }
}