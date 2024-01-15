package com.utick.dvtcodingassessment.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.ui.components.CurrentWeatherComponent
import com.utick.dvtcodingassessment.ui.components.ForecastWeatherComponent
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    weatherViewModel : WeatherViewModel = koinViewModel()
) {
    weatherViewModel.getCurrentWeather(Coord(24.6580, 25.9077))
    weatherViewModel.getForecastWeather(Coord(24.6580, 25.9077))

    Column {
        val modifier = Modifier.fillMaxSize()
        Row(modifier = Modifier.weight(1f)) {
            CurrentWeatherComponent(modifier)
        }
        Row (modifier = Modifier.weight(1f)){
            ForecastWeatherComponent(modifier)
        }


    }

}
