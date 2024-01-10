package com.utick.dvtcodingassessment.data.response.currentWeather

import com.utick.dvtcodingassessment.model.Main
import com.utick.dvtcodingassessment.model.Weather
import com.utick.dvtcodingassessment.model.Wind
import kotlinx.serialization.Serializable


@Serializable
data class CurrentWeatherResponse (

  val coord : Coord? = Coord(),
  val weather : ArrayList<Weather> = arrayListOf(),
  val base : String? = null,
  val main : Main? = Main(),
  val visibility : Int? = null,
  val wind: Wind? = Wind(),
  val rain: Rain? = Rain(),
  val clouds : Clouds? = Clouds(),
  val dt : Int?  = null,
  val sys : Sys? = Sys(),
  val timezone : Int?= null,
  val id : Int? = null,
  val name : String? = null,
  val cod : Int? = null

)