package com.utick.dvtcodingassessment.data.repository

import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse
import com.utick.dvtcodingassessment.network.ApiClient
import com.utick.dvtcodingassessment.network.BASE_URL
import com.utick.dvtcodingassessment.network.CURRENT
import com.utick.dvtcodingassessment.network.FORECAST5
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
                    parameters.append("lat", coord.lat.toString())
                    parameters.append("lon", coord.lon.toString())

                }
            }.body()
            Either.Right(currentWeatherResponse)
        } catch (e: Exception) {
            Either.Left(Failure.ServerError)
        }

    }

    override suspend fun getFiveDayForecast(coord: Coord): Either<Failure, ForecastWeatherResponse> {
        return try {
            val forecastWeatherResponse : ForecastWeatherResponse = client.api.get("$BASE_URL$FORECAST5") {
                url {
                    parameters.append("appid", "abc123")
                    parameters.append("lat", coord.lat.toString())
                    parameters.append("lon", coord.lon.toString())

                }
            }.body()
            Either.Right(forecastWeatherResponse)
        } catch (e: Exception) {
            Either.Left(Failure.ServerError)
        }
    }
}

