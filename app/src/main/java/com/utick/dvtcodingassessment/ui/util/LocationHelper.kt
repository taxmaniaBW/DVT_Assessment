package com.utick.dvtcodingassessment.ui.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.ui.WeatherViewModel

class LocationHelper(private val context: Context, private val weatherViewModel: WeatherViewModel): LocationCallback() {

    private var request: LocationRequest
    private var locationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private val timeInterval: Long = 1800000

    init {
        // getting the location client
        request = createRequest()
    }

    private fun createRequest(): LocationRequest =

        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, timeInterval).apply {
            setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
            setWaitForAccurateLocation(true)
        }.build()

    @SuppressLint("MissingPermission")
    fun startLocationTracking() =
        locationClient.requestLocationUpdates(request, this, Looper.getMainLooper())


    fun stopLocationTracking() {
        locationClient.flushLocations()
        locationClient.removeLocationUpdates(this)
    }

    override fun onLocationResult(location: LocationResult) {
        location.lastLocation?.let {
            weatherViewModel.setLocation(Coord(it.latitude, it.longitude))
        }

    }

    override fun onLocationAvailability(availability: LocationAvailability) {
        // TODO: react on the availability change
    }

}