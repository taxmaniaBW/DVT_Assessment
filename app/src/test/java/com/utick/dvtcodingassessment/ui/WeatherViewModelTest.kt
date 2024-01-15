package com.utick.dvtcodingassessment.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.utick.dvtcodingassessment.BaseTest
import com.utick.dvtcodingassessment.InstantTaskExecutorRuleForJUnit5
import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.apiService.GetFiveDayForecast
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.repository.WeatherRepository
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.ui.view.HomeView
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import com.utick.dvtcodingassessment.util.NetworkHandler
import com.utick.dvtcodingassessment.util.toRight
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.TestWatcher

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
        weatherViewModel = WeatherViewModel(getCurrentWeather, dispatcher, homeView)
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
        weatherViewModel = WeatherViewModel(getCurrentWeather, dispatcher, homeView)
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
