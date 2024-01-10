package com.utick.dvtcodingassessment.data.repository

import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.model.currentWeather.Coord
import com.utick.dvtcodingassessment.model.currentWeather.CurrentWeatherModel
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure

interface WeatherRepository  {
    suspend fun getCurrentWeather(coord: Coord): Either<Failure, CurrentWeatherResponse>
}