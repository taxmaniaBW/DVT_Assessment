package com.utick.dvtcodingassessment.data.response.currentWeather


import com.utick.dvtcodingassessment.data.local.CurrentWeatherData
import com.utick.dvtcodingassessment.util.asTemperatureString
import kotlinx.serialization.Serializable
import java.util.Date


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

fun CurrentWeatherResponse.minimized(): CurrentWeatherData {
  return CurrentWeatherData(
    temp = main?.temp?.asTemperatureString(),
    tempMin = main?.tempMin?.asTemperatureString(),
    tempMax = main?.tempMax?.asTemperatureString(),
    condition = weather[0].main,
    updatedDate = System.currentTimeMillis()
  )
}