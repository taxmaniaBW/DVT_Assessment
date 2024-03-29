package com.utick.dvtcodingassessment.data.response.currentWeather

import kotlinx.serialization.Serializable


@Serializable

data class Weather (

  val id: Int?    = null,
  val main: String? = null,
  val description: String? = null,
  val icon: String? = null

)