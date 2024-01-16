package com.utick.dvtcodingassessment.data.response.forecastresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main (
    val temp: Double,

    @SerialName("feels_like")
    val feelsLike: Double? = null,

    @SerialName("temp_min")
    val tempMin: Double? = null,

    @SerialName("temp_max")
    val tempMax: Double? = null,

    val pressure: Long? = null,

    @SerialName("sea_level")
    val seaLevel: Long? = null,

    @SerialName("grnd_level")
    val grndLevel: Long? = null,

    val humidity: Long? = null,

    @SerialName("temp_kf")
    val tempKf: Double? = null
)