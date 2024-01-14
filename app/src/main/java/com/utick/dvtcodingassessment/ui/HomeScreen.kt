package com.utick.dvtcodingassessment.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.utick.dvtcodingassessment.R
import com.utick.dvtcodingassessment.data.response.forecastresponse.Day
import com.utick.dvtcodingassessment.ui.components.CurrentWeatherComponent
import com.utick.dvtcodingassessment.ui.components.ForecastWeatherComponent
import com.utick.dvtcodingassessment.ui.model.DayRowModel


@Composable
fun HomeScreen(
) {
    Column {
        val modifier = Modifier.fillMaxSize()
        CurrentWeatherComponent(modifier = Modifier.fillMaxHeight(1f))
        ForecastWeatherComponent(modifier = Modifier.fillMaxHeight(1f))


    }

}