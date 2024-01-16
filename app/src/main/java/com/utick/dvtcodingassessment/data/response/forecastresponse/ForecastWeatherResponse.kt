package com.utick.dvtcodingassessment.data.response.forecastresponse

import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeatherResponse (
    val cod: String? = null,
    val message: Long? = null,
    val cnt: Long? = null,
    val list: List<Day>,
    val city: City? = null
)