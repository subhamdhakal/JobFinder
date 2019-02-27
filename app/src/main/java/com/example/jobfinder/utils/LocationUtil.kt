package com.example.jobfinder.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat.*
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker.checkCallingOrSelfPermission
import android.util.Log
import android.widget.Toast
import com.example.jobfinder.api.LocationInterface

private const val PERMISSION_REQUEST = 10


class LocationUtil(val c: Context) {

    lateinit var locationInterface: LocationInterface
    lateinit var locationManager: LocationManager
    private var hasGps = false
    private var hasNetwork = false
    private var locationGps: Location? = null
    private var permissions =
        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)


    fun setListener(locationInterface: LocationInterface) {
        this.locationInterface = locationInterface
    }

    fun Permission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(permissions)) {
                getLocation()
            } else {
                requestPermissions(c as Activity, permissions, PERMISSION_REQUEST)
            }
        } else {
            getLocation()
        }

    }

    private fun getLocation() {
        if (!checkPermission(permissions)) return
        locationManager = c.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (hasGps || hasNetwork) {

            if (hasGps) {
                Log.d("CodeAndroidLocation", "hasGps")
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20, 0F, object : LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        if (location != null) {
                            locationGps = location
                            Log.d("inside if", "hasGps")

                            locationInterface.getLocation(locationGps)
                            locationManager.removeUpdates(this)
                        }
                    }


                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

                    }

                    override fun onProviderEnabled(provider: String?) {

                    }

                    override fun onProviderDisabled(provider: String?) {

                    }
                })

//                    val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//                    if (localGpsLocation != null)
//                        locationGps = localGpsLocation
            }
//                if (hasNetwork) {
//                    Log.d("CodeAndroidLocation", "hasGps")
//                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0F, object : LocationListener {
//                        override fun onLocationChanged(location: Location?) {
//                            if (location != null) {
//                                locationNetwork = location
//
//                                Log.d("CodeAndroidLocation", " Network Latitude : " + locationNetwork!!.latitude)
//                                Log.d("CodeAndroidLocation", " Network Longitude : " + locationNetwork!!.longitude)
//                            }
//                        }
//
//                        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
//
//                        }
//
//                        override fun onProviderEnabled(provider: String?) {
//
//                        }
//
//                        override fun onProviderDisabled(provider: String?) {
//
//                        }
//
//                    })

//                    val localNetworkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//                    if (localNetworkLocation != null)
//                        locationNetwork = localNetworkLocation
//                }

//                if(locationGps!= null && locationNetwork!= null){
//                    if(locationGps!!.accuracy > locationNetwork!!.accuracy){
//
//                        Log.d("CodeAndroidLocation", " Network Latitude : " + locationNetwork!!.latitude)
//                        Log.d("CodeAndroidLocation", " Network Longitude : " + locationNetwork!!.longitude)
//                    }else{
//
//                        Log.d("CodeAndroidLocation", " GPS Latitude : " + locationGps!!.latitude)
//                        Log.d("CodeAndroidLocation", " GPS Longitude : " + locationGps!!.longitude)
//                    }
//                }

        } else {
            c.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }


    }

    private fun checkPermission(permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(c as Activity, permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST) {
            var allSuccess = true
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allSuccess = false
                    val requestAgain =
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(
                            c as Activity,
                            permissions[i]
                        )
                    if (requestAgain) {
                        Toast.makeText(c as Activity, "Permission denied", Toast.LENGTH_SHORT).show()
                    } else {

                        Toast.makeText(c as Activity, "Please enable location from settings", Toast.LENGTH_SHORT).show()


                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts("package", c.packageName, null)
                        intent.data = uri
                        c.startActivity(intent)
                    }
                }
            }
            if (allSuccess)
                getLocation()

        }
    }

    //test test


}