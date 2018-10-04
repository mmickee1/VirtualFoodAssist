package com.android.example.virtualfoodassist

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.location.*
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.Road
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.bonuspack.routing.RoadNode
import org.osmdroid.bonuspack.clustering.GridMarkerClusterer
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class Location : Fragment(), LocationListener, SensorEventListener {

    private var imageCompass: ImageView? = null
    private var currentDegree = 0f
    private var mCompassSensorManager: SensorManager? = null
    internal lateinit var degrees: TextView

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.location, container, false)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).setTitle(R.string.location)
        setHasOptionsMenu(true)


        val map = view.findViewById<MapView>(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setBuiltInZoomControls(true)
        map.setMultiTouchControls(true)
        map.controller.setZoom(12.0)
        val startPoint = GeoPoint(60.17, 24.95)
        map.controller.setCenter(GeoPoint(60.17, 24.95))


        val ctx = (activity as AppCompatActivity).applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        val lm = (activity as AppCompatActivity).getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(activity as Activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(activity as Activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 0)
            ActivityCompat.requestPermissions(activity as Activity, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION), 0)
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1f, this)


        val myLocation = MyLocationNewOverlay(map)
        myLocation.enableMyLocation()
        myLocation.enableFollowLocation()
        map.overlays.add(myLocation)
        //map.animate() to myLocation.myLocation


        imageCompass = view.findViewById(R.id.img_compass) as ImageView
        degrees = view.findViewById(R.id.txt_angle) as TextView
        mCompassSensorManager = context!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager


        //NAVIGATION
      /*  val startMarker = Marker(map)
        startMarker.position = startPoint
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        map.overlays.add(startMarker)

        val roadManager: RoadManager = OSRMRoadManager(this.context)
        val waypoints = ArrayList<GeoPoint>()
        waypoints.add(startPoint)
        val endPoint = GeoPoint(48.4, -1.9)
        waypoints.add(endPoint)
        val road: Road = roadManager.getRoad(waypoints)
        val roadOverlay: Polyline = RoadManager.buildRoadOverlay(road)
        map.getOverlays().add(roadOverlay)
        map.invalidate()

        val nodeIcon: Drawable = getResources().getDrawable(R.drawable.marker_node);
        for (i in 0..road.mNodes.size - 1) {
            val node: RoadNode = road.mNodes.get(i)
            val nodeMarker: Marker = Marker (map)
            nodeMarker.setPosition(node.mLocation)
            nodeMarker.setIcon(nodeIcon)
            nodeMarker.setTitle("Step " + i)
            map.getOverlays().add(nodeMarker)
            nodeMarker.setSnippet(node.mInstructions)
            nodeMarker.setSubDescription(Road.getLengthDurationText(this.context, node.mLength, node.mDuration))
            val icon = resources.getDrawable(R.drawable.ic_continue)
            nodeMarker.image = icon

        }*/

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onLocationChanged(p0: Location?) {
        Log.d("GEOLOCATION", "new latitude: ${p0?.latitude} and longitude: ${p0?.longitude}")
        // txt_map_info.text = "Latitude: ${p0?.latitude}, Longitude: ${p0?.longitude}, Speed: ${p0?.speedAccuracyMetersPerSecond}, Altitude: ${p0?.altitude}"

        val latitude = p0!!.latitude
        val longitude = p0.longitude
        val speed = p0.speed
        val speedMS = p0.speedAccuracyMetersPerSecond

        val lat = "%.2f".format(latitude)
        val lon = "%.2f".format(longitude)
        val sms = "%.2f".format(speedMS)

        val geoPoint = GeoPoint(latitude, longitude)

        txt_map_info.text = "Latitude: ${lat}, Longitude: ${lon}, Speed: ${sms} m/s"

        map.animate().to(geoPoint)
        map.mapCenter
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home
            -> {
                //NavUtils.navigateUpFromSameTask(Info());
                //NavUtils.navigateUpTo()
                /*  val upIntent = Intent(activity, MainActivity::class.java)
                  if (NavUtils.shouldUpRecreateTask(activity!!, upIntent)) {
                      activity!!.finish()
                  } else {
                      NavUtils.navigateUpTo(activity!!, upIntent)
                  } */
                val i = Intent(activity, MainActivity::class.java)
                startActivity(i)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        mCompassSensorManager!!.registerListener(this, mCompassSensorManager!!.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()

        mCompassSensorManager!!.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        // get the angle around the z-axis rotated
        val degree = Math.round(event.values[0]).toFloat()

        degrees.text = "Heading: " + java.lang.Float.toString(degree) + " degrees"

        val rotationAnimation = RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f)
        rotationAnimation.duration = 210
        rotationAnimation.fillAfter = true
        imageCompass!!.startAnimation(rotationAnimation)
        currentDegree = -degree

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}
