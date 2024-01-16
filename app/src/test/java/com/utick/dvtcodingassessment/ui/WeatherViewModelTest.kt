package com.utick.dvtcodingassessment.ui

import android.graphics.Color
import com.utick.dvtcodingassessment.BaseTest
import com.utick.dvtcodingassessment.InstantTaskExecutorRuleForJUnit5
import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.apiService.GetFiveDayForecast
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.ui.data.Content
import com.utick.dvtcodingassessment.ui.data.CurrentWeatherTheme
import com.utick.dvtcodingassessment.ui.data.CurrentWeatherUI
import com.utick.dvtcodingassessment.ui.data.ForecastWeatherUI
import com.utick.dvtcodingassessment.ui.view.HomeView
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import com.utick.dvtcodingassessment.util.asTemperatureString
import com.utick.dvtcodingassessment.util.getDayOfWeek
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.coEvery
import io.mockk.every
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
        every {homeView.getTheme(expected.weather[0])} returns CurrentWeatherTheme(0, androidx.compose.ui.graphics.Color.Red)

        weatherViewModel.getCurrentWeather(Coord(12.6,12.3))
        val currentWeatherUI = CurrentWeatherUI(
            loading = false,
            tempMax = expected.main?.tempMax?.asTemperatureString(),
            tempMin = expected.main?.tempMin?.asTemperatureString(),
            temp = expected.main?.temp?.asTemperatureString(),
            condition = expected.weather[0].main,
            theme  = homeView.getTheme(expected.weather[0]))


        advanceUntilIdle()
        weatherViewModel.currentWeatherUi.value.shouldBe(currentWeatherUI)

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
        val currentWeatherUI = CurrentWeatherUI(
            error = Failure.NetworkConnection,
            hasError = true
        )

        advanceUntilIdle()
        weatherViewModel.currentWeatherUi.value.shouldBe(currentWeatherUI)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get forecast weather success`() = runTest {

        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val getFiveDayForecast = GetFiveDayForecast(networkHandler, weatherRepository, dispatcher)
        weatherViewModel = WeatherViewModel(getCurrentWeather, getFiveDayForecast, dispatcher, homeView)
        val expected = forecastResponse()
        coEvery { weatherRepository.getFiveDayForecast(any()) } returns Either.Right(expected)
        coEvery { networkHandler.isNetworkAvailable() } returns true


        weatherViewModel.getForecastWeather(Coord(12.6,12.3))
        val contentList = arrayListOf<Content>()

        val byGroup = expected.list.groupBy { getDayOfWeek(it.dt) }
        byGroup.entries.forEach {
            every { homeView.getWeatherIcon(it.value[0]) } returns 0
            contentList.add(
                Content(
                    temp = it.value[0].main.temp.asTemperatureString(),
                    day = it.key,
                    icon = homeView.getWeatherIcon(it.value[0]),
                )
            )
        }
        val forecastWeatherUI = ForecastWeatherUI(
            loading = false,
            content = contentList)


        advanceUntilIdle()
        weatherViewModel.forecastWeatherUi.value.shouldBe(forecastWeatherUI)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get forecast weather network failure`() = runTest {

        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val getFiveDayForecast = GetFiveDayForecast(networkHandler, weatherRepository, dispatcher)
        weatherViewModel = WeatherViewModel(getCurrentWeather, getFiveDayForecast, dispatcher, homeView)
        val expected = forecastResponse()
        coEvery { weatherRepository.getFiveDayForecast(any()) } returns Either.Right(expected)
        coEvery { networkHandler.isNetworkAvailable() } returns false

        weatherViewModel.getForecastWeather(Coord(12.6,12.3))
        val forecastWeatherUI = ForecastWeatherUI(
            error = Failure.NetworkConnection,
            hasError = true
        )

        advanceUntilIdle()
        weatherViewModel.forecastWeatherUi.value.shouldBe(forecastWeatherUI)

    }

}
