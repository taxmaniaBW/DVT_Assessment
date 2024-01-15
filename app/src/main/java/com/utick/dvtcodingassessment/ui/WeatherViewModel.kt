package com.utick.dvtcodingassessment.ui

import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.apiService.GetFiveDayForecast
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.data.response.forecastresponse.Day
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse
import com.utick.dvtcodingassessment.ui.data.DayRowModel
import com.utick.dvtcodingassessment.ui.data.CurrentWeatherUI
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
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val homeView: HomeView
): BaseViewModel() {

    private val _currentWeather = MutableStateFlow<CurrentWeatherResponse?> (null)
    val currentWeather = _currentWeather.asStateFlow()

    private val _fiveDayForecast = MutableStateFlow<ForecastWeatherResponse?> (null)
    val fiveDayForecast = _currentWeather.asStateFlow()

    private val _dayData = MutableStateFlow<DayRowModel?>(null)
    val dayData = _dayData.asStateFlow()

    private val _currentWeatherUi = MutableStateFlow(CurrentWeatherUI(loading = false))
    val currentWeatherUi = _currentWeatherUi.asStateFlow()



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
            error = failure
        )

    }

    private fun handleFiveDayForecast(forecastWeatherResponse: ForecastWeatherResponse) {
        _fiveDayForecast.value = forecastWeatherResponse
    }

    fun getDayData(day: Day) {
        val weatherIcon = homeView.getWeatherIcon(day)
        val dayName = getDayOfWeek(day.dt)
        val maxTemp = day.main.tempMax

        _dayData.value = DayRowModel(dayName,weatherIcon,maxTemp)
    }
}