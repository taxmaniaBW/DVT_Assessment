package com.utick.dvtcodingassessment

import com.utick.dvtcodingassessment.data.repository.WeatherRepository
import com.utick.dvtcodingassessment.data.response.currentWeather.Clouds
import com.utick.dvtcodingassessment.data.response.currentWeather.Coord
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.data.response.currentWeather.Main
import com.utick.dvtcodingassessment.data.response.currentWeather.Rain
import com.utick.dvtcodingassessment.data.response.currentWeather.Sys
import com.utick.dvtcodingassessment.data.response.currentWeather.Weather
import com.utick.dvtcodingassessment.data.response.currentWeather.Wind
import com.utick.dvtcodingassessment.util.NetworkHandler
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

abstract class BaseTest {

    private val standardCoroutineDispatcher = StandardTestDispatcher()
    private val testCoroutineScope = TestScope(standardCoroutineDispatcher)
    val networkHandler = mockk<NetworkHandler>()
    val weatherRepository = mockk<WeatherRepository>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun init() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(standardCoroutineDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    fun currentWeatherResponse() = CurrentWeatherResponse(
        Coord(19.555, 19.555),
        arrayListOf(Weather(501, "Rain", "moderate rain", "10d")),
        "stations",
        Main(298.48, 298.74, 297.56, 300.05, 1015, 64, 1015, 933),
        1000,
        Wind(0.62, 349, 1.18),
        Rain(3.16),
        Clouds(100),
        1661870592,
        Sys(2, 2075663, "IT", 1661834187, 1661882248),
        7200,
        3163858,
        "Zocca",
        200
    )
}