package com.example.tourism_app

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.*

class popnotify : AppCompatActivity() {

    val MY_PREFS_NAME = "MyPrefsFile"

    val key:Int?=null
    var theno:Int?=null
    var lat: String? = null
    var log: String? = null
    var address: String? =null
    var fusedLocationProviderClient: FusedLocationProviderClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popnotify)
//        val txt = findViewById<TextView>(R.id.textdata)
        getlocation()



    }

    private fun getlocation() {


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }

        fusedLocationProviderClient!!.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val geocoder = Geocoder(this@popnotify, Locale.getDefault())
                try {
                    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)


                    lat = addresses?.get(0)?.latitude.toString()
                    log = addresses?.get(0)?.longitude.toString()
                    address = addresses!!.get(0).getAddressLine(0)
                    //Toast.makeText(applicationContext,address.toString(), Toast.LENGTH_LONG).show()
                    val mynumber = 51972
                    println("The key for value  $mynumber")

                    val map = HashMap<String, Int>() //Creating HashMap
                    map["sarasbaug"] = 50114
                    map["shaniwarwada"] = 51972
                    map["sss"] = 757
                    val keys = arrayOfNulls<String>(map.size)
                    val values: Array<Int?> = arrayOfNulls(map.size)
                    var index = 0
                    for ((key, value) in map) {
                        keys[index] = key
                        values[index] = value
                        index++
                    }
//                    val mynumber = 300
                    var dis = values[0]?.minus(mynumber)?.let { Math.abs(it) }
                    var indx = 0
                    for (c in 1 until values.size) {
                        val cdistance = values[c]?.minus(mynumber)?.let { Math.abs(it) }
                        if (cdistance != null) {
                            if (cdistance < dis!!) {
                                indx = c
                                dis = cdistance
                            }
                        }
                    }
                    val theno = values[indx]
                    println("near no is$theno")
                    for ((key, value) in map) {
                        if (value == theno) {
                            println("The key for value $theno is $key")
                            break
                            senddata(key.toString())
                        }
                    }


                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }


    }

    private fun senddata(place: String) {

        val prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
        val email = prefs.getString("email", "No name defined") //"No name defined" is the default value.

        val se = send(applicationContext,email.toString(),"Place Order Successfully",place);
        se.execute()

    }




}