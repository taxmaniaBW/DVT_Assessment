package com.utick.dvtcodingassessment.util

import android.content.Context
import android.net.ConnectivityManager
import kotlin.math.roundToInt

val Context.connectivityManager: ConnectivityManager
    get() =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


fun Double.asTemperatureString() : String {
    return (this.roundToInt().toString() + "\u00B0")
}
