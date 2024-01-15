package com.utick.dvtcodingassessment.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.utick.dvtcodingassessment.R
import com.utick.dvtcodingassessment.data.model.Coord
import com.utick.dvtcodingassessment.ui.components.CurrentWeatherComponent
import com.utick.dvtcodingassessment.ui.components.ForecastWeatherComponent
import com.utick.dvtcodingassessment.ui.data.CurrentWeatherTheme
import com.utick.dvtcodingassessment.ui.data.CurrentWeatherUI
import com.utick.dvtcodingassessment.ui.theme.SUNNY
import com.utick.dvtcodingassessment.ui.util.amentFamily
import com.utick.dvtcodingassessment.util.asTemperatureString
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    weatherViewModel : WeatherViewModel = koinViewModel()
) {
    weatherViewModel.getCurrentWeather(Coord(24.6580, 25.9077))
    Column {
        val modifier = Modifier.fillMaxSize()
        Row(modifier = Modifier.weight(1f)) {
            CurrentWeatherComponent(modifier)
        }
        Row (modifier = Modifier.weight(1f)){
            CurrentWeatherComponent(modifier)
        }


    }

}

@Preview
@Composable
fun HomeScreenPreview(

) {


    Column {
        val uiModel = CurrentWeatherUI(
            loading = false,
            23.0.asTemperatureString(),
            "Sunny",
            theme = CurrentWeatherTheme(R.drawable.sea_sunnypng, SUNNY)
        )
        val modifier = Modifier.fillMaxSize()


    }

}

