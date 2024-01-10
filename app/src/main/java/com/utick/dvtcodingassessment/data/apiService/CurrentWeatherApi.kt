package com.utick.dvtcodingassessment.data.apiService

import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.model.currentWeather.Coord
import com.utick.dvtcodingassessment.util.ApiResponse
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure

interface CurrentWeatherApi {
    suspend fun getCurrentWeather(coord: Coord): Either<Failure, CurrentWeatherResponse>
}