package com.utick.dvtcodingassessment.apiService

import com.utick.dvtcodingassessment.BaseTest
import com.utick.dvtcodingassessment.InstantTaskExecutorRuleForJUnit5
import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.repository.WeatherRepository
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import com.utick.dvtcodingassessment.util.NetworkHandler
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
@ExtendWith(InstantTaskExecutorRuleForJUnit5::class)
class GetCurrentWeatherTest: BaseTest() {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_networkAvailable_returnsWeatherResponse() = runTest {
        // Arrange
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val useCase = GetCurrentWeather(networkHandler, weatherRepository, dispatcher)
        val expectedResponse = mockk<CurrentWeatherResponse>()
        coEvery { weatherRepository.getCurrentWeather(any()) } returns Either.Right(expectedResponse)
        coEvery { networkHandler.isNetworkAvailable() } returns true

        val result = useCase.run(Coord(12.7,6.7) )

        advanceUntilIdle()
        result.shouldBe(Either.Right(expectedResponse))

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_networkUnAvailable_returnsFailure() = runTest {

        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val useCase = GetCurrentWeather(networkHandler, weatherRepository, dispatcher)
        coEvery { networkHandler.isNetworkAvailable() } returns false

        val result = useCase.run(Coord(12.7,6.7) )
        val expected = Failure.NetworkConnection

        advanceUntilIdle()
        result.shouldBe(Either.Left(expected))

    }

}