package com.utick.dvtcodingassessment.data.repository

import com.utick.dvtcodingassessment.data.DataSource
import com.utick.dvtcodingassessment.data.local.CurrentWeatherData
import com.utick.dvtcodingassessment.data.local.ForecastWeatherData
import com.utick.dvtcodingassessment.data.local.WeatherDao
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.data.response.currentWeather.minimized
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse
import com.utick.dvtcodingassessment.data.response.forecastresponse.minimized
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
                            private val weatherDao: WeatherDao,
                            private val dispatcher : CoroutineDispatcher = Dispatchers.Default,): WeatherRepository {
    override suspend fun getCurrentWeather(coord: Coord): Either<Failure, CurrentWeatherData> {

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
                    val currentWeatherData = currentWeatherResponse.minimized()
                    weatherDao.saveCurrentWeather(currentWeatherData)
                    Either.Right(currentWeatherData)
                }
            } catch (e: Exception) {
                getLocalCurrentWeather(Failure.ServerError) //Fallback to local
            }




    }

    override suspend fun getFiveDayForecast(coord: Coord): Either<Failure, List<ForecastWeatherData>> {
        return try {
            val forecastWeatherResponse : ForecastWeatherResponse = client.api.get("$BASE_URL$FORECAST5") {
                url {
                    parameters.append(APPID, APIKEY)
                    parameters.append(LAT, coord.lat.toString())
                    parameters.append(LON, coord.lon.toString())
                    parameters.append(UNITS, METRIC)

                }
            }.body()
            val forecastWeatherData = forecastWeatherResponse.minimized()
            weatherDao.saveForecastWeather(forecastWeatherData)
            Either.Right(forecastWeatherData)
        } catch (e: Exception) {
            getLocalForecastWeather(Failure.ServerError)
        }
    }

    override suspend fun getLocalCurrentWeather(reason: Failure): Either<Failure, CurrentWeatherData> {
        val currentWeatherData = weatherDao.getCurrentWeather()
        currentWeatherData?.let {
            return Either.Right(it)
        }
        if(reason != Failure.None) {
            return Either.Left(reason) // We return failure response from API Call
        }
        return Either.Left(Failure.NetworkConnection) //Default failure is network connection to allow user to try remote call

    }

    override suspend fun getLocalForecastWeather(reason: Failure): Either<Failure, List<ForecastWeatherData>> {
        val forecastWeatherData = weatherDao.getForecastWeather()
        forecastWeatherData?.let {
            return Either.Right(it)
        }
        if(reason != Failure.None) {
            return Either.Left(reason) // We return failure response from API Call
        }
        return Either.Left(Failure.NetworkConnection) //Default failure is network connection to allow user to try remote call
    }
}

