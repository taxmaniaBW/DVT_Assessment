package com.utick.dvtcodingassessment.ui.util

import android.Manifest
import com.utick.dvtcodingassessment.data.model.Coord

sealed class LocationEvent: Permission() {


        data class CurrentLocationReceived(val coord: Coord) : LocationEvent()
        data object Initial : LocationEvent()

        data class Permission(val permissionEvent: com.utick.dvtcodingassessment.ui.util.Permission): LocationEvent()



}