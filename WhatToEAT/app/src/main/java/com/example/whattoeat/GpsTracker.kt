package com.example.whattoeat

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.content.ContextCompat

class GpsTracker() : Service(), LocationListener {

    private final lateinit var mContext: Context
    lateinit var mLocation: Location
    private var mLatitude: Double = 0.0
    private var mLongitude: Double = 0.0

    private final val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 10F
    private final val MIN_TIME_BW_UPDATES: Long = 6000
    protected lateinit var locationManager: LocationManager

    constructor(context: Context) : this() {
        this.mContext = context
        getLocation()
    }


    public fun getLocation(): Location? {
        try {
            locationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            var isGpsEnabled: Boolean =
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            var isNetworkEnabled: Boolean =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!isGpsEnabled && !isNetworkEnabled) {

            } else {
                var hasFineLocationPermission: Int = ContextCompat.checkSelfPermission(
                    mContext,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
                var hasCoarseLocationPermission: Int = ContextCompat.checkSelfPermission(
                    mContext,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )

                if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

                } else {
                    return null
                }

                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES,
                        this
                    )

                    if (locationManager != null) {
                        mLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (mLocation != null) {
                            mLatitude = mLocation.latitude
                            mLongitude = mLocation.longitude
                        }
                    }
                }

                if (isGpsEnabled) {
                    if (mLocation != null) {
                        locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES,
                            this
                        )
                        if (locationManager != null) {
                            mLocation =
                                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (mLocation != null) {
                                mLatitude = mLocation.latitude
                                mLongitude = mLocation.longitude
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return mLocation
    }


    public fun getLatitude(): Double {
        if (mLocation != null) {
            mLatitude = mLocation.latitude
        }
        return mLatitude
    }

    public fun getLongitude(): Double {
        if (mLocation != null) {
            mLongitude = mLocation.longitude
        }
        return mLongitude
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onLocationChanged(p0: Location?) {
        Log.d("GPS Tracker" , p0.toString())
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

