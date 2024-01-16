package com.utick.dvtcodingassessment.data.response.forecastresponse

import kotlinx.serialization.Serializable

@Serializable
data class Weather (
    val id: Long? = null,
    val main: String,
    val description: String? = null,
    val icon: String? = null
)