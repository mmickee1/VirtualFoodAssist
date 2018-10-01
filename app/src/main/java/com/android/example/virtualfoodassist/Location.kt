package com.android.example.virtualfoodassist

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.location.*
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class Location : Fragment(), LocationListener {

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
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onLocationChanged(p0: Location?) {
        Log.d("GEOLOCATION", "new latitude: ${p0?.latitude} and longitude: ${p0?.longitude}")
        txt_map_info.text = "Latitude: ${p0?.latitude}, Longitude: ${p0?.longitude}, Speed: ${p0?.speedAccuracyMetersPerSecond}, Altitude: ${p0?.altitude}"

        val latitude = p0!!.latitude
        val longitude = p0.longitude
        val speed = p0.speed

        val geoPoint = GeoPoint(latitude, longitude)

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
}
