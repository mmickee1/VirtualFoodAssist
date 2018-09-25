package com.android.example.virtualfoodassist

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.google.ar.core.*
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.ar_fragment.*
import java.util.concurrent.CompletableFuture

class ArHelper : AppCompatActivity() {

    lateinit var pasta: ModelRenderable
    lateinit var fragment: ArFragment
    lateinit var fitToScanImageView: ImageView
    lateinit var pastaRenderable: ModelRenderable
    lateinit var testRenderable: ModelRenderable
    lateinit var modelUri: Uri
    lateinit var renderableFuture: CompletableFuture<ModelRenderable>
    private var pastaFound = false

    fun setUp() {

        fragment = supportFragmentManager?.findFragmentById(R.id.arimage_fragment) as ArFragment
        fitToScanImageView = findViewById<ImageView>(R.id.fit_to_scan_img)

        val pasta = ModelRenderable.builder()
                .setSource(this, Uri.parse("andy.sfb"))
                .build()
        pasta.thenAccept { it -> this.pasta = it }

        fragment.arSceneView.scene.addOnUpdateListener { frameTime -> onUpdate(frameTime) }
    }

    fun onUpdate(frameTime: FrameTime) {
        fragment.onUpdate(frameTime)
        val arFrame = fragment.arSceneView.arFrame
        if (arFrame == null || arFrame.camera.trackingState != TrackingState.TRACKING) {
            return
        }
        val updatedAugmentedImages = arFrame.getUpdatedTrackables(AugmentedImage::class.java)
        updatedAugmentedImages.forEach {
            when (it.trackingState) {
                TrackingState.PAUSED -> {

                }
                TrackingState.STOPPED -> {

                }
                TrackingState.TRACKING -> {
                    val anchors = it.anchors
                    if (anchors.isEmpty()) {
                        fitToScanImageView.visibility = View.GONE
                        val pose = it.centerPose
                        val anchor = it.createAnchor(pose)
                        val anchorNode = AnchorNode(anchor)
                        anchorNode.setParent(fragment.arSceneView.scene)
                        val imgNode = TransformableNode(fragment.transformationSystem)
                        imgNode.setParent(anchorNode)

                        if (it.name == "pasta1") {
                            imgNode.renderable = pastaRenderable
                            pastaFound = true
                            Toast.makeText(this@ArHelper, "*Pasta*", Toast.LENGTH_SHORT).show()
                            ButtonClick.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    class AugmentedImageFragmentREAL : ArFragment() {

        private val SAMPLE_IMAGE_DATABASE = "sample_database.imgdb"

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            planeDiscoveryController.hide()
            planeDiscoveryController.setInstructionView(null)
            //arSceneView.planeRenderer.isEnabled = false
            return inflater.inflate(R.layout.ar_fragment, container, false)
        }

        override fun getSessionConfiguration(session: Session?): Config {
            val config = super.getSessionConfiguration(session)
            setupAugmentedImageDatabase(config, session)
            return config
        }

        private fun setupAugmentedImageDatabase(config: Config, session: Session?) {
            val augmentedImageDb: AugmentedImageDatabase
            val assetManager = context!!.assets

            // Pasta
            val pasta1 = assetManager.open("pasta/pasta1.jpg")
            val augmentedImageBitmapPasta1 = BitmapFactory.decodeStream(pasta1)

            augmentedImageDb = AugmentedImageDatabase(session)

            augmentedImageDb.addImage("pasta1", augmentedImageBitmapPasta1)

            config.augmentedImageDatabase = augmentedImageDb
        }
    }
}