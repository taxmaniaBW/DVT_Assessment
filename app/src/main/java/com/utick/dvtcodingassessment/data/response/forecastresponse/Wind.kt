package com.utick.dvtcodingassessment.data.response.forecastresponse

import kotlinx.serialization.Serializable

@Serializable
data class Wind (
    val speed: Double,
    val deg: Long,
    val gust: Double
)