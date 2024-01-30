package com.utick.dvtcodingassessment.data.repository

import com.utick.dvtcodingassessment.data.DataSource
import com.utick.dvtcodingassessment.data.local.CurrentWeatherData
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure

interface WeatherRepository  {
    suspend fun getCurrentWeather(coord: Coord, dataSource: DataSource = DataSource.REMOTE): Either<Failure, CurrentWeatherData>
    suspend fun getFiveDayForecast(coord: Coord, dataSource: DataSource = DataSource.REMOTE): Either<Failure, ForecastWeatherResponse>

    suspend fun getLocalCurrentWeather(): Either<Failure, CurrentWeatherData>
}