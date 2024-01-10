package com.utick.dvtcodingassessment.data.response.currentWeather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rain (

  @SerialName("1h" ) val oneHour : Double? = null

)