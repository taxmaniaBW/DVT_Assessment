package com.utick.dvtcodingassessment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utick.dvtcodingassessment.R
import com.utick.dvtcodingassessment.ui.WeatherViewModel
import com.utick.dvtcodingassessment.ui.data.Content
import com.utick.dvtcodingassessment.util.asTemperatureString
import com.utick.dvtcodingassessment.util.getDayOfWeek
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForecastWeatherComponent(modifier: Modifier, weatherViewModel : WeatherViewModel = koinViewModel()) {
    val currentWeatherUI by weatherViewModel.currentWeatherUi.collectAsState()
    val forecastWeatherUI by weatherViewModel.forecastWeatherUi.collectAsState()
    val bgColor = currentWeatherUI.theme?.color ?: Color.DarkGray



    Column(modifier = Modifier.background(color = bgColor).fillMaxSize().verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(48.dp))
        forecastWeatherUI.content.forEach { content ->
            DayItem(day = content)
        }
    }


}
