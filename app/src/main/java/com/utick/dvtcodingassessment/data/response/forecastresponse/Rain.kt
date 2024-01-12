package com.utick.dvtcodingassessment.data.response.forecastresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rain (
    @SerialName("3h")
    val the3H: Double
)