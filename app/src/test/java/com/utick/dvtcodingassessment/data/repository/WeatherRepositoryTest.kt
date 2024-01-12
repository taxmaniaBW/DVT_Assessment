package com.utick.dvtcodingassessment.data.repository

import com.utick.dvtcodingassessment.BaseTest
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.response.MockedWeatherResponse
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.network.ApiClient
import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import io.kotest.matchers.shouldBe
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class WeatherRepositoryTest : BaseTest(), KoinTest {

    @BeforeEach
    fun setUp() {
    }


    @Test
    fun `test getCurrentWeather success`() = runTest(UnconfinedTestDispatcher()) {
        val mockEngine = MockEngine { _ ->
            respond(
                content = MockedWeatherResponse.CURRENT,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val apiClient = ApiClient(mockEngine)
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        weatherRepository = WeatherRepositoryImpl(apiClient, dispatcher)
        val format = Json { isLenient = true }
        val expected =
            Either.Right(format.decodeFromString<CurrentWeatherResponse>(MockedWeatherResponse.CURRENT))
        val result = weatherRepository.getCurrentWeather(Coord(12.5, 12.3))
        advanceUntilIdle()
        result.shouldBe(expected)
    }


    @Test
    fun `test getCurrentWeather server error`() = runTest(UnconfinedTestDispatcher()) {
        val mockEngine = MockEngine { _ ->
            respond(
                content = MockedWeatherResponse.CURRENT,
                status = HttpStatusCode.InternalServerError,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val apiClient = ApiClient(mockEngine)
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        weatherRepository = WeatherRepositoryImpl(apiClient, dispatcher)
        val expected = Either.Left(Failure.ServerError)
        val result = weatherRepository.getCurrentWeather(Coord(12.5, 12.3))
        advanceUntilIdle()
        result.shouldBe(expected)
    }

}


