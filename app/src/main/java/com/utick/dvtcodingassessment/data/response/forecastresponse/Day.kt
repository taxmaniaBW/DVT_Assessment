package com.utick.dvtcodingassessment.data.response.forecastresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Day (
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Long,
    val pop: Double,
    val rain: Rain? = null,
    val sys: Sys,

    @SerialName("dt_txt")
    val dtTxt: String
)