package com.android.example.virtualfoodassist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.android.example.virtualfoodassist.DB.DBHandler
import com.android.example.virtualfoodassist.DB.Food
import com.google.ar.core.AugmentedImage
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.ar_fragment.*
import kotlinx.android.synthetic.main.location_google.*

@Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA", "CanBeVal")
class AugmentedReality : AppCompatActivity() {

    private lateinit var fragment: ArFragment
    private lateinit var fitToScanImageView: ImageView
    private lateinit var pastaRenderable: ModelRenderable
    private lateinit var pizzaRenderable: ModelRenderable

    private val pastaUrl = "https://www.valio.fi/reseptit/ryhmat/pastat/"
    private val pizzaUrl = "https://www.valio.fi/reseptit/pizza/"

    private val contexti = this
    var db = DBHandler(contexti)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ar_fragment)
        createArView()
    }

    private fun createArView() {

        Toast.makeText(this@AugmentedReality, R.string.scan_for_recipe, Toast.LENGTH_LONG).show()

        fragment = supportFragmentManager.findFragmentById(R.id.arimage_fragment) as ArFragment
        fitToScanImageView = findViewById(R.id.fit_to_scan_img)

        // Pasta
        val pasta = ModelRenderable.builder()
                .setSource(this, Uri.parse("pastaObject3.sfb"))
                .build()
        pasta.thenAccept { it -> this.pastaRenderable = it }

        // Pizza
        val pizza = ModelRenderable.builder()
                .setSource(this, Uri.parse("pizzaObject.sfb"))
                .build()
        pizza.thenAccept { it -> this.pizzaRenderable = it }

        fragment.arSceneView.scene.addOnUpdateListener { frameTime -> onUpdate(frameTime) }
        AugmentedImageFragment()

        ButtonClick.visibility = View.GONE
        ButtonClick.setOnClickListener {
            //changed location from fragment to activity so commenting the fragment out...
            //supportFragmentManager.beginTransaction().replace(R.id.arimage_fragment, Location()).addToBackStack(null).commit()
            val intent = Intent(baseContext, LocationGoogle::class.java)
            startActivity(intent)
            ButtonClick.visibility = View.GONE
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
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

                        if (it.name == "pasta1" || it.name == "pasta2" || it.name == "pasta3" || it.name == "pasta4" ||
                                it.name == "pasta5" || it.name == "pasta6" || it.name == "pasta7" || it.name == "pasta8" ||
                                it.name == "pasta9" || it.name == "pasta10" || it.name == "pasta11" || it.name == "pasta12" ||
                                it.name == "pasta13" || it.name == "pasta14") {
                            imgNode.renderable = pastaRenderable
                            imgNode.setOnTapListener { _, _ ->
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(pastaUrl)
                                startActivity(intent)
                            }
                            Toast.makeText(this@AugmentedReality, R.string.click_object_for_pasta_recipe, Toast.LENGTH_LONG).show()
                            ButtonClick.visibility = View.VISIBLE
                            val food = Food("Pasta")
                            db.insertData(food)

                        } else if (it.name == "pizza1" || it.name == "pizza2" || it.name == "pizza3" || it.name == "pizza4" ||
                                it.name == "pizza5" || it.name == "pizza6" || it.name == "pizza7" || it.name == "pizza8" ||
                                it.name == "pizza9" || it.name == "pizza10") {
                            imgNode.renderable = pizzaRenderable
                            imgNode.setOnTapListener { _, _ ->
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(pizzaUrl)
                                startActivity(intent)
                            }
                            Toast.makeText(this@AugmentedReality, R.string.click_object_for_pizza_recipe, Toast.LENGTH_LONG).show()
                            ButtonClick.visibility = View.VISIBLE
                            val food = Food("Pizza")
                            db.insertData(food)
                        }

                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStackImmediate()
    }
}