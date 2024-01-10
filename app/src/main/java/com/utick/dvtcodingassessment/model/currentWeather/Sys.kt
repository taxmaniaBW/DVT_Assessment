package com.utick.dvtcodingassessment.model.currentWeather


import kotlinx.serialization.Serializable

@Serializable
data class Sys (

  val type    : Int?    = null,
  val id      : Int?    = null,
  val country : String? = null,
  val sunrise : Int?    = null,
  val sunset  : Int?    = null

)