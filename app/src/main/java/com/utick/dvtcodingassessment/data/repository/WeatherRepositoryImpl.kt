package com.utick.dvtcodingassessment.data.repository

import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse
import com.utick.dvtcodingassessment.network.ApiClient
import com.utick.dvtcodingassessment.network.BASE_URL
import com.utick.dvtcodingassessment.network.CURRENT
import com.utick.dvtcodingassessment.network.FORECAST5
import com.utick.dvtcodingassessment.util.APIKEY
import com.utick.dvtcodingassessment.util.APPID
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import com.utick.dvtcodingassessment.util.LAT
import com.utick.dvtcodingassessment.util.LON
import com.utick.dvtcodingassessment.util.METRIC
import com.utick.dvtcodingassessment.util.UNITS
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class WeatherRepositoryImpl(private val client : ApiClient,
                            private val dispatcher : CoroutineDispatcher = Dispatchers.Default): WeatherRepository {
    override suspend fun getCurrentWeather(coord: Coord): Either<Failure, CurrentWeatherResponse> {

           return try {
                runBlocking(dispatcher) {
                    val currentWeatherResponse: CurrentWeatherResponse =
                        client.api.get("$BASE_URL$CURRENT") {
                            url {
                                parameters.append(APPID, APIKEY)
                                parameters.append(LAT, coord.lat.toString())
                                parameters.append(LON, coord.lon.toString())
                                parameters.append(UNITS, METRIC)

                            }
                        }.body()
                    Either.Right(currentWeatherResponse)
                }
            } catch (e: Exception) {
                Either.Left(Failure.ServerError)
            }




    }

    override suspend fun getFiveDayForecast(coord: Coord): Either<Failure, ForecastWeatherResponse> {
        return try {
            val forecastWeatherResponse : ForecastWeatherResponse = client.api.get("$BASE_URL$FORECAST5") {
                url {
                    parameters.append(APPID, APIKEY)
                    parameters.append(LAT, coord.lat.toString())
                    parameters.append(LON, coord.lon.toString())
                    parameters.append(UNITS, METRIC)

                }
            }.body()
            Either.Right(forecastWeatherResponse)
        } catch (e: Exception) {
            Either.Left(Failure.ServerError)
        }
    }
}

