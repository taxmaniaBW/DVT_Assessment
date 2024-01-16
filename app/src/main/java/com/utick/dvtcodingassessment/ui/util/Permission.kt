package com.utick.dvtcodingassessment.ui.util

import android.Manifest.permission.*

sealed class Permission(vararg val permissions: String) {

    //Bundled Common Location Permissions
    object Location : Permission(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)

    data object Initial: Permission()


        data object Granted : Permission()
        data object Denied : Permission()
        data object ShowRationale : Permission()

        data class ShouldDirectToSettings(val permission: Permission = Location) : Permission()

    companion object {
        fun from(permission: String) = when (permission) {
            ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION -> Location
            else -> throw IllegalArgumentException("Unknown permission: $permission")
        }
    }
}