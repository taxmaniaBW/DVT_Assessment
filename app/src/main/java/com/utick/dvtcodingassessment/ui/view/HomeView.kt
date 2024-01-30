package com.utick.dvtcodingassessment.ui.view

import com.utick.dvtcodingassessment.R
import com.utick.dvtcodingassessment.data.response.currentWeather.Weather
import com.utick.dvtcodingassessment.data.response.forecastresponse.Day
import com.utick.dvtcodingassessment.ui.data.CurrentWeatherTheme
import com.utick.dvtcodingassessment.ui.data.WeatherCondition
import com.utick.dvtcodingassessment.ui.theme.CLOUDY
import com.utick.dvtcodingassessment.ui.theme.RAINY
import com.utick.dvtcodingassessment.ui.theme.SUNNY

class HomeView: BaseView() {

    /**
     * @param day : Day model, could be current or from the forecast
     * @return icon resource Id based on weather condition
     */
    fun getWeatherIcon(condition: String): Int{
        return when(WeatherCondition.fromValue(condition)){
            WeatherCondition.SUNNY -> R.drawable.clear
            WeatherCondition.CLOUDY -> R.drawable.partlysunny
            WeatherCondition.RAINY -> R.drawable.rain
            else -> {R.drawable.clear}
        }

    }

    /**
     * Get display theme based on current weather
     * @return CurrentWeatherTheme
     */
    fun getTheme(currentCondition: String?): CurrentWeatherTheme {
        return when(currentCondition?.let { WeatherCondition.fromValue(it) }){
            WeatherCondition.SUNNY -> CurrentWeatherTheme(R.drawable.sea_sunnypng, SUNNY)
            WeatherCondition.CLOUDY -> CurrentWeatherTheme(R.drawable.sea_cloudy, CLOUDY)
            WeatherCondition.RAINY -> CurrentWeatherTheme(R.drawable.sea_rainy, RAINY)
            else -> {
                CurrentWeatherTheme(R.drawable.sea_sunnypng,  SUNNY)}
        }
    }
}