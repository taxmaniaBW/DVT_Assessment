package com.utick.dvtcodingassessment.data.response.forecastresponse

import kotlinx.serialization.Serializable

@Serializable
data class Coord (
    val lat: Double,
    val lon: Double
)