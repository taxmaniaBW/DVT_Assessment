package com.utick.dvtcodingassessment.data.response.forecastresponse

import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeatherResponse (
    val cod: String,
    val message: Long,
    val cnt: Long,
    val list: List<Day>,
    val city: City
)