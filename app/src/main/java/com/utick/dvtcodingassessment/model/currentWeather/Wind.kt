package com.utick.dvtcodingassessment.model


import kotlinx.serialization.Serializable

@Serializable
data class Wind (

  val speed : Double? = null,
  val deg   : Int?    = null,
  val gust  : Double? = null

)