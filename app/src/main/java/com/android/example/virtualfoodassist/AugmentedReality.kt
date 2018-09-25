package com.android.example.virtualfoodassist

import android.animation.ObjectAnimator
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.google.ar.core.AugmentedImage
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.PlaneRenderer
import com.google.ar.sceneform.rendering.Texture
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.ar_fragment.*
import java.util.concurrent.CompletableFuture

class AugmentedReality : AppCompatActivity() {

    lateinit var pasta: ModelRenderable

    lateinit var fragment: ArFragment
    lateinit var fitToScanImageView: ImageView
    lateinit var pastaRenderable: ModelRenderable
    lateinit var testRenderable: ModelRenderable
    lateinit var modelUri: Uri
    lateinit var renderableFuture: CompletableFuture<ModelRenderable>

    private var rotatingHeart = false
    private var pastaFound = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ar_fragment)

        Log.d("test", "AR")
        Toast.makeText(this@AugmentedReality, "Welcome! Scan an item for a recipe!", Toast.LENGTH_LONG).show()

        fragment = supportFragmentManager.findFragmentById(R.id.arimage_fragment) as ArFragment
        fitToScanImageView = findViewById<ImageView>(R.id.fit_to_scan_img)

        val pasta = ModelRenderable.builder()
                .setSource(this, Uri.parse("andy.sfb"))
                .build()
        pasta.thenAccept { it -> this.pasta = it }

        fragment.arSceneView.scene.addOnUpdateListener { frameTime -> onUpdate(frameTime) }

        AugmentedImageFragment()
    }

    private fun onUpdate(frameTime: FrameTime) {
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
            }
        }

        updatedAugmentedImages.forEach {
            when (it.trackingState) {
                TrackingState.TRACKING -> {
                    var anchors = it.anchors
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
                            Toast.makeText(this@AugmentedReality, "*Pasta*", Toast.LENGTH_SHORT).show()
                            ButtonClick.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    //object animator helper methods..
    private fun resume(objectAnimator: ObjectAnimator) {
        objectAnimator.resume()
        rotatingHeart = true
    }

    private fun pause(objectAnimator: ObjectAnimator) {
        objectAnimator.pause()
        rotatingHeart = false

    }

    private fun start(objectAnimator: ObjectAnimator) {
        objectAnimator.start()
        rotatingHeart = false
    }
}