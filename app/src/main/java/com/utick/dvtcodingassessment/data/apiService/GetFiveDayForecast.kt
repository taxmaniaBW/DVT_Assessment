package com.utick.dvtcodingassessment.data.apiService

import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.repository.WeatherRepository
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse
import com.utick.dvtcodingassessment.interactor.UseCase
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import com.utick.dvtcodingassessment.util.NetworkHandler

class GetFiveDayForecast(
    private val networkHandler: NetworkHandler,
    private val weatherRepository: WeatherRepository):
    UseCase<ForecastWeatherResponse, Coord>() {
    override suspend fun run(params: Coord): Either<Failure, ForecastWeatherResponse> {
        return when (networkHandler.isNetworkAvailable()){
            true -> weatherRepository.getFiveDayForecast(params)
            false -> Either.Left(Failure.NetworkConnection)
        }

    }
}