package com.utick.dvtcodingassessment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.apiService.GetFiveDayForecast
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.data.response.currentWeather.CurrentWeatherResponse
import com.utick.dvtcodingassessment.data.response.forecastresponse.ForecastWeatherResponse

class WeatherViewModel(
    private val getCurrentWeather: GetCurrentWeather,
    private val getFiveDayForecast: GetFiveDayForecast
): BaseViewModel() {

    private val _currentWeather: MutableLiveData<CurrentWeatherResponse> = MutableLiveData()
    val currentWeather: LiveData<CurrentWeatherResponse> = _currentWeather

    private val _fiveDayForecast: MutableLiveData<ForecastWeatherResponse> = MutableLiveData()
    val fiveDayForecast: LiveData<ForecastWeatherResponse> = _fiveDayForecast

    fun getCurrentWeather(coord: Coord) =
        getCurrentWeather(coord, viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handleCurrentWeather
            )
        }
    private fun handleCurrentWeather(currentWeatherResponse: CurrentWeatherResponse) {
        _currentWeather.postValue(currentWeatherResponse)
    }

    fun getFiveDayForecast(coord: Coord) =
        getFiveDayForecast(coord, viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handleFiveDayForecast
            )
        }
    private fun handleFiveDayForecast(forecastWeatherResponse: ForecastWeatherResponse) {
        _fiveDayForecast.postValue(forecastWeatherResponse)
    }
}