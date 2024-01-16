package com.utick.dvtcodingassessment.data.response.forecastresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Day (
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds? = null,
    val wind: Wind? = null,
    val visibility: Long? = null,
    val pop: Double? = null,
    val rain: Rain? = null,
    val sys: Sys? =null,

    @SerialName("dt_txt")
    val dtTxt: String? = null
)