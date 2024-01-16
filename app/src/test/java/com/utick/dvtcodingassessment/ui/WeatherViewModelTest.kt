package com.utick.dvtcodingassessment.ui

import com.utick.dvtcodingassessment.BaseTest
import com.utick.dvtcodingassessment.InstantTaskExecutorRuleForJUnit5
import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.apiService.GetFiveDayForecast
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.ui.view.HomeView
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorRuleForJUnit5::class)
class WeatherViewModelTest: BaseTest() {

    private lateinit var weatherViewModel: WeatherViewModel

    @MockK
    private lateinit var getCurrentWeather: GetCurrentWeather

    @MockK
    private lateinit var getFiveDayForecast: GetFiveDayForecast


    @MockK
    private lateinit var homeView: HomeView



    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {


    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get current weather success`() = runTest {

        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val getCurrentWeather = GetCurrentWeather(networkHandler, weatherRepository, dispatcher)
        weatherViewModel = WeatherViewModel(getCurrentWeather, getFiveDayForecast, dispatcher, homeView)
        val expected = currentWeatherResponse()
        coEvery { weatherRepository.getCurrentWeather(any()) } returns Either.Right(expected)
        coEvery { networkHandler.isNetworkAvailable() } returns true

        weatherViewModel.getCurrentWeather(Coord(12.6,12.3))

        advanceUntilIdle()
        weatherViewModel.currentWeather.value.shouldBe(expected)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get current weather network failure`() = runTest {

        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val getCurrentWeather = GetCurrentWeather(networkHandler, weatherRepository, dispatcher)
        weatherViewModel = WeatherViewModel(getCurrentWeather, getFiveDayForecast, dispatcher, homeView)
        val expected = currentWeatherResponse()
        coEvery { weatherRepository.getCurrentWeather(any()) } returns Either.Right(expected)
        coEvery { networkHandler.isNetworkAvailable() } returns false

        weatherViewModel.getCurrentWeather(Coord(12.6,12.3))

        advanceUntilIdle()
        weatherViewModel.failure.value.shouldBe(Failure.NetworkConnection)

    }






    @Test
    fun getFiveDayForecast() {
    }

    @Test
    fun testGetCurrentWeather() {
    }

    @Test
    fun testGetFiveDayForecast() {
    }

}
