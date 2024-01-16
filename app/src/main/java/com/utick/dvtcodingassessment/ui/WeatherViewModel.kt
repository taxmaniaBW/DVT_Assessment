package com.utick.dvtcodingassessment.ui

import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.apiService.GetFiveDayForecast
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.data.response.forecastresponse.Day
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse
import com.utick.dvtcodingassessment.ui.BaseViewModel
import com.utick.dvtcodingassessment.ui.data.Content
import com.utick.dvtcodingassessment.ui.data.DayRowModel
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


    private val _dayData = MutableStateFlow<DayRowModel?>(null)
    val dayData = _dayData.asStateFlow()

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
    private fun handleCurrentWeather(currentWeatherResponse: CurrentWeatherResponse) {
        _currentWeatherUi.value = CurrentWeatherUI(
            loading = false,
            temp = currentWeatherResponse.main?.temp?.asTemperatureString(),
            condition = currentWeatherResponse.weather[0].main,
            theme  = homeView.getTheme(currentWeatherResponse.weather[0]))
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
                ::handleCurrentWeatherFailure,
                ::handleFiveDayForecast
            )
        }

    }

    /**
     * Successful Response, build forecast weather ui Model
     */

    private fun handleFiveDayForecast(forecastWeatherResponse: ForecastWeatherResponse) {
        val contentList = arrayListOf<Content>()
        for (day in forecastWeatherResponse.list) {
            contentList.add(
                Content(
                temp = day.main.tempMax.asTemperatureString(),
                day = getDayOfWeek(day.dt),
                icon = homeView.getWeatherIcon(day),
            )
            )
        }
        _forecastWeatherUi.value = ForecastWeatherUI(
            loading = false,
            content = contentList
        )
    }

    private fun handleCForecastWeatherFailure(failure: Failure){
        _forecastWeatherUi.value = ForecastWeatherUI(
            loading = false,
            error = failure,
            hasError = true
        )

    }

    fun getDayData(day: Day) {
        val weatherIcon = homeView.getWeatherIcon(day)
        val dayName = getDayOfWeek(day.dt)
        val maxTemp = day.main.tempMax

        _dayData.value = DayRowModel(dayName,weatherIcon,maxTemp)
    }
}