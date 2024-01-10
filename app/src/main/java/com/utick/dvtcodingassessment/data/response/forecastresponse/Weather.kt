package com.utick.dvtcodingassessment.data.response.forecastresponse

data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)