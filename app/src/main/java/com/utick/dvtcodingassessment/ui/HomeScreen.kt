package com.utick.dvtcodingassessment.ui


import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.ui.components.CurrentWeatherComponent
import com.utick.dvtcodingassessment.ui.components.ForecastWeatherComponent
import com.utick.dvtcodingassessment.ui.util.LocationHelper
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    weatherViewModel : WeatherViewModel = koinViewModel(),
    context: Context
) {

    val locationHelper = LocationHelper(context, weatherViewModel)
    locationHelper.startLocationTracking()

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
