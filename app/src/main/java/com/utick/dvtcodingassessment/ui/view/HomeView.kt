package com.utick.dvtcodingassessment.ui.view

import com.utick.dvtcodingassessment.data.response.currentWeather.Weather
import com.utick.dvtcodingassessment.data.response.forecastresponse.Day
import com.utick.dvtcodingassessment.data.response.forecastresponse.Main
import com.utick.dvtcodingassessment.ui.model.DayRowModel
import com.utick.dvtcodingassessment.ui.model.WeatherCondition

class HomeView {

    /**
     * @param day : Day model, could be current or from the forecast
     * @return icon resource Id based on weather condition
     */
    fun getWeatherIcon(day: Day): Int{
        return when(WeatherCondition.fromValue(day.weather[0].main)){
            WeatherCondition.SUNNY -> 0
            WeatherCondition.CLOUDY -> 1
            WeatherCondition.RAINY -> 2
            else -> {0}
        }

    }

    /**
     * @return Resource Id of back ground image based on current weather
     */
    fun getBgImage(currentCondition: Weather): Int{
        return when(currentCondition.main?.let { WeatherCondition.fromValue(it) }){
            WeatherCondition.SUNNY -> 0
            WeatherCondition.CLOUDY -> 1
            WeatherCondition.RAINY -> 2
            else -> {0}
        }
    }
}