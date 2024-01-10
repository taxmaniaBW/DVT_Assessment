package com.utick.dvtcodingassessment.data.response.forecastresponse

import kotlinx.serialization.Serializable

@Serializable
data class City (
    val id: Long,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Long,
    val timezone: Long,
    val sunrise: Long,
    val sunset: Long
)