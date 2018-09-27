package com.android.example.virtualfoodassist

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.location.*
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class Location : AppCompatActivity(), LocationListener {


    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ctx = applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        setContentView(R.layout.location)

        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setBuiltInZoomControls(true)
        map.setMultiTouchControls(true)
        map.controller.setZoom(9.0)
        map.controller.setCenter(GeoPoint(60.17, 24.95))


        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 0)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION), 0)
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1f, this)


        val myLocation = MyLocationNewOverlay(map)
        myLocation.enableMyLocation()
        myLocation.enableFollowLocation()
        map.overlays.add(myLocation)
        //map.animate() to myLocation.myLocation

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onLocationChanged(p0: Location?) {
        Log.d("GEOLOCATION", "new latitude: ${p0?.latitude} and longitude: ${p0?.longitude}")
        txt_info.text = "Latitude: ${p0?.latitude}, Longitude: ${p0?.longitude}, Speed: ${p0?.speedAccuracyMetersPerSecond}, Altitude: ${p0?.altitude}"

        val latitude = p0!!.latitude
        val longitude = p0.longitude
        val speed = p0.speed

        val geoPoint = GeoPoint(latitude, longitude)

        map.animate().to(geoPoint)
        map.mapCenter

        Toast.makeText(this, "Location obtained", Toast.LENGTH_LONG).show()
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

}
