package com.utick.dvtcodingassessment.data.repository

import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.model.currentWeather.Coord
import com.utick.dvtcodingassessment.network.ApiClient
import com.utick.dvtcodingassessment.network.BASE_URL
import com.utick.dvtcodingassessment.network.CURRENT
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import io.ktor.client.call.body
import io.ktor.client.request.get

class WeatherRepositoryImpl(private val client : ApiClient): WeatherRepository {
    override suspend fun getCurrentWeather(coord: Coord): Either<Failure, CurrentWeatherResponse> {

        return try {
            val currentWeatherResponse : CurrentWeatherResponse = client.api.get("$BASE_URL$CURRENT") {
                url {
                    parameters.append("appid", "abc123")
                }
            }.body()
            Either.Right(currentWeatherResponse)
        } catch (e: Exception) {
            Either.Left(Failure.ServerError)
        }

    }
}

