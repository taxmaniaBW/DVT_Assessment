package com.utick.dvtcodingassessment.model


import kotlinx.serialization.Serializable

@Serializable
data class Main (

  val temp      : Double? = null,
  val feelsLike : Double? = null,
  val tempMin   : Double? = null,
  val tempMax   : Double? = null,
  val pressure  : Int?    = null,
  val humidity  : Int?    = null,
  val seaLevel  : Int?    = null,
  val grndLevel : Int?    = null

)