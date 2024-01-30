package com.utick.dvtcodingassessment.data.apiService

import com.utick.dvtcodingassessment.data.local.CurrentWeatherData
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.repository.WeatherRepository
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.interactor.UseCase
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import com.utick.dvtcodingassessment.util.NetworkHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetCurrentWeather(
    private val networkHandler: NetworkHandler,
    private val weatherRepository: WeatherRepository,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
):
    UseCase<CurrentWeatherData, Coord>(ioDispatcher) {
    override suspend fun run(params: Coord): Either<Failure, CurrentWeatherData> {
        return when (networkHandler.isNetworkAvailable()){
            true -> weatherRepository.getCurrentWeather(params)
            false -> Either.Left(Failure.NetworkConnection)
        }

    }
}