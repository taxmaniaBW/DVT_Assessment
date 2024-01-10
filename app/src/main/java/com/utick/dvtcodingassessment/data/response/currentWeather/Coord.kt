package com.utick.dvtcodingassessment.data.response.currentWeather

import kotlinx.serialization.Serializable

@Serializable
data class Coord (

  val lon : Double? = null,
  val lat : Double? = null

)