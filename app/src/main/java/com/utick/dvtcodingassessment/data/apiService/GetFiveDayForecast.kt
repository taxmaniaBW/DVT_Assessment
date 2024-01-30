package com.utick.dvtcodingassessment.data.apiService

import com.utick.dvtcodingassessment.data.local.ForecastWeatherData
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.repository.WeatherRepository
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse
import com.utick.dvtcodingassessment.interactor.UseCase
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import com.utick.dvtcodingassessment.util.NetworkHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetFiveDayForecast(
    private val networkHandler: NetworkHandler,
    private val weatherRepository: WeatherRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ):
    UseCase<List<ForecastWeatherData>, Coord>(ioDispatcher) {
    override suspend fun run(params: Coord): Either<Failure, List<ForecastWeatherData>> {
        return when (networkHandler.isNetworkAvailable()){
            true -> weatherRepository.getFiveDayForecast(params)
            false -> weatherRepository.getLocalForecastWeather()
        }

    }
}