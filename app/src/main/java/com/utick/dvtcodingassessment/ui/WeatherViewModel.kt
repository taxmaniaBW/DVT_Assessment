package com.utick.dvtcodingassessment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.apiService.GetFiveDayForecast
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WeatherViewModel(
    val getCurrentWeatherService: GetCurrentWeather,
    private val getFiveDayForecast: GetFiveDayForecast,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): BaseViewModel() {

    private val _currentWeather: MutableLiveData<CurrentWeatherResponse> = MutableLiveData()
    val currentWeather: LiveData<CurrentWeatherResponse> = _currentWeather

    private val _fiveDayForecast: MutableLiveData<ForecastWeatherResponse> = MutableLiveData()
    val fiveDayForecast: LiveData<CurrentWeatherResponse> = _currentWeather

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
}