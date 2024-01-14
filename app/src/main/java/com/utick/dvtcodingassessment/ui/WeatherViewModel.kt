package com.utick.dvtcodingassessment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.apiService.GetFiveDayForecast
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.data.response.forecastresponse.Day
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse
import com.utick.dvtcodingassessment.ui.model.DayRowModel
import com.utick.dvtcodingassessment.ui.view.HomeView
import com.utick.dvtcodingassessment.util.getDayOfWeek
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WeatherViewModel(
    val getCurrentWeatherService: GetCurrentWeather,
    private val getFiveDayForecast: GetFiveDayForecast,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val homeView: HomeView
): BaseViewModel() {

    private val _currentWeather: MutableLiveData<CurrentWeatherResponse> = MutableLiveData()
    val currentWeather: LiveData<CurrentWeatherResponse> = _currentWeather

    private val _fiveDayForecast: MutableLiveData<ForecastWeatherResponse> = MutableLiveData()
    val fiveDayForecast: LiveData<CurrentWeatherResponse> = _currentWeather

    private val _dayData: MutableLiveData<DayRowModel> = MutableLiveData()
    val dayData: LiveData<DayRowModel> = _dayData

    fun getCurrentWeather(coord: Coord) {
     getCurrentWeatherService(coord, dispatcher = ioDispatcher) {
                it.fold(
                    ::handleFailure,
                    ::handleCurrentWeather
                )
            }

    }
    private fun handleCurrentWeather(currentWeatherResponse: CurrentWeatherResponse) {
        _currentWeather.postValue(currentWeatherResponse)
    }

    private fun handleFiveDayForecast(forecastWeatherResponse: ForecastWeatherResponse) {
        _fiveDayForecast.postValue(forecastWeatherResponse)
    }

    fun getDayData(day: Day) {
        val weatherIcon = homeView.getWeatherIcon(day)
        val dayName = getDayOfWeek(day.dt)
        val maxTemp = day.main.tempMax

        _dayData.postValue(DayRowModel(dayName,weatherIcon,maxTemp))
    }
}