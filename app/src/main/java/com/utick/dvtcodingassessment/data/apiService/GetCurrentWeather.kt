package com.utick.dvtcodingassessment.data.apiService

import com.utick.dvtcodingassessment.data.repository.WeatherRepository
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.interactor.UseCase
import com.utick.dvtcodingassessment.model.currentWeather.Coord
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import com.utick.dvtcodingassessment.util.NetworkHandler

class GetCurrentWeather(
    private val networkHandler: NetworkHandler,
    private val weatherRepository: WeatherRepository):
    UseCase<CurrentWeatherResponse, Coord>() {
    override suspend fun run(params: Coord): Either<Failure, CurrentWeatherResponse> {
        return when (networkHandler.isNetworkAvailable()){
            true -> weatherRepository.getCurrentWeather(params)
            false -> Either.Left(Failure.NetworkConnection)
        }

    }
}