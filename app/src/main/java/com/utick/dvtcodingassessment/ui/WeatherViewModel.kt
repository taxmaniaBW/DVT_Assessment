package com.utick.dvtcodingassessment.ui

import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.apiService.GetFiveDayForecast
import com.utick.dvtcodingassessment.data.local.CurrentWeatherData
import com.utick.dvtcodingassessment.data.local.ForecastWeatherData
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse
import com.utick.dvtcodingassessment.ui.data.Content
import com.utick.dvtcodingassessment.ui.data.CurrentWeatherUI
import com.utick.dvtcodingassessment.ui.data.ForecastWeatherUI
import com.utick.dvtcodingassessment.ui.view.HomeView
import com.utick.dvtcodingassessment.util.Failure
import com.utick.dvtcodingassessment.util.asTemperatureString
import com.utick.dvtcodingassessment.util.getDayOfWeek
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WeatherViewModel(
    val getCurrentWeatherService: GetCurrentWeather,
    private val getFiveDayForecast: GetFiveDayForecast,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val homeView: HomeView
): BaseViewModel() {


    private val _currentLocation = MutableStateFlow<Coord?>(null)
    val currentLocation = _currentLocation.asStateFlow()

    private val _currentWeatherUi = MutableStateFlow(CurrentWeatherUI(loading = false))
    val currentWeatherUi = _currentWeatherUi.asStateFlow()

    private val _forecastWeatherUi = MutableStateFlow(ForecastWeatherUI(loading = false))
    val forecastWeatherUi = _forecastWeatherUi.asStateFlow()



    fun getCurrentWeather(coord: Coord) {
        _currentWeatherUi.value = CurrentWeatherUI(loading = true)
     getCurrentWeatherService(coord, dispatcher = ioDispatcher) {
                it.fold(
                    ::handleCurrentWeatherFailure,
                    ::handleCurrentWeather
                )
            }

    }

    /**
     * @param currentWeatherResponse: API Response
     * Build UI Model to pass only relevant data to activity
     */
    private fun handleCurrentWeather(currentWeatherData: CurrentWeatherData) {
        _currentWeatherUi.value = CurrentWeatherUI(
            loading = false,
            data = currentWeatherData,
            theme  = homeView.getTheme(currentWeatherData.condition))
    }

    private fun handleCurrentWeatherFailure(failure: Failure){
        _currentWeatherUi.value = CurrentWeatherUI(
            error = failure,
            hasError = true,
            loading = false
        )

    }

    fun getForecastWeather(coord: Coord) {
        _forecastWeatherUi.value = ForecastWeatherUI(loading = true)
        getFiveDayForecast(coord, dispatcher = ioDispatcher) {
            it.fold(
                ::handleForecastWeatherFailure,
                ::handleFiveDayForecast
            )
        }

    }

    /**
     * Successful Response, api returns a long list not suitable for UI
     * build forecast weather ui Model an provide activity with only whats needed
     */

    private fun handleFiveDayForecast(forecastWeatherData: List<ForecastWeatherData>) {
        val contentList = arrayListOf<Content>()
        forecastWeatherData.forEach {
            contentList.add(
                Content(
                    temp = it.temp,
                    day = it.day,
                    icon = homeView.getWeatherIcon(it.condition.toString())))
        }
        _forecastWeatherUi.value = ForecastWeatherUI(
            loading = false,
            content = contentList
        )
    }

    private fun handleForecastWeatherFailure(failure: Failure){
        _forecastWeatherUi.value = ForecastWeatherUI(
            loading = false,
            error = failure,
            hasError = true
        )

    }

    /**
     * @param coord : Current location, sent from locationhelper
     */

    fun setLocation(coord: Coord){
        _currentLocation.value = coord
        getCurrentWeather(coord)
        getForecastWeather(coord)
    }
}