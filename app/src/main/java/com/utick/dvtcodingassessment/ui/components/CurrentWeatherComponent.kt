package com.utick.dvtcodingassessment.ui.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.utick.dvtcodingassessment.R
import com.utick.dvtcodingassessment.ui.WeatherViewModel
import com.utick.dvtcodingassessment.ui.data.CurrentWeatherTheme
import com.utick.dvtcodingassessment.ui.data.CurrentWeatherUI
import com.utick.dvtcodingassessment.ui.theme.SUNNY
import com.utick.dvtcodingassessment.ui.util.amentFamily
import com.utick.dvtcodingassessment.util.asTemperatureString
import org.koin.androidx.compose.koinViewModel
import shimmerLoadingAnimation

@Composable
fun CurrentWeatherComponent(modifier: Modifier, weatherViewModel : WeatherViewModel = koinViewModel()) {

    val uiModel by weatherViewModel.currentWeatherUi.collectAsState()


        if(uiModel.loading){
            CurrentWeatherLoading()
        }
        else {
            if(uiModel.hasError) {
                Log.e("CurrentWeatherComponent", "CurrentWeatherComponent: ${uiModel.error}")
            }
            else {
                CurrentWeatherScreen(uiModel)
            }


    }

}

@Composable
fun CurrentWeatherLoading() {
    val uiModel = CurrentWeatherUI(
        loading = false,
        23.0.asTemperatureString(),
        condition = "Sunny",
        theme = CurrentWeatherTheme(R.drawable.sea_sunnypng, SUNNY)
    )
    Column {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()


        ) {
            uiModel.theme?.let { theme ->
                Image(
                    modifier = Modifier
                        .matchParentSize()
                        .shimmerLoadingAnimation(),
                    painter = painterResource(id = theme.bgImage),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds

                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                uiModel.temp?.let { temp ->
                    Text(
                        modifier = Modifier.shimmerLoadingAnimation(),
                        text = temp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = 60.sp, fontFamily = amentFamily)
                    )
                }
                Text(
                    modifier = Modifier.shimmerLoadingAnimation(),
                    text = uiModel.condition!!,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    style = TextStyle(fontSize = 50.sp, fontFamily = amentFamily)
                )
            }


        }
        Box(modifier = Modifier
            .weight(0.2f)
            .background(SUNNY)
            .fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp), horizontalArrangement = Arrangement.SpaceAround
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight()
                            .align(Alignment.Center),
                        verticalArrangement = Arrangement.Center
                    ) {
                        uiModel.temp?.let { temp ->
                            Text(
                                text = temp.toString(),
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                                    .shimmerLoadingAnimation()
                            )
                        }
                        Text(modifier = Modifier.shimmerLoadingAnimation(), text = "min", textAlign = TextAlign.Center, color = Color.White)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight()
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        uiModel.temp?.let { temp ->
                            Text(modifier = Modifier.shimmerLoadingAnimation(),text = temp, textAlign = TextAlign.Center, color = Color.White)
                        }
                        Text(modifier = Modifier.shimmerLoadingAnimation(),text = "Current", textAlign = TextAlign.Center, color = Color.White)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight()
                            .align(Alignment.Center),

                        horizontalAlignment = Alignment.End
                    ) {
                        uiModel.temp?.let { temp ->
                            Box(
                                modifier = Modifier.wrapContentHeight()

                            ) {
                                Text(
                                    text = temp.toString(),
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    modifier = Modifier.wrapContentHeight()

                                )
                            }
                        }
                        Text(text = "max", textAlign = TextAlign.Center, color = Color.White)
                    }
                }
            }
        }

        Divider(
            color = Color.White,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun CurrentWeatherScreen(uiModel: CurrentWeatherUI){
    Column {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()

        ) {
            uiModel.theme?.let { theme ->
                Image(
                    modifier = Modifier.matchParentSize(),
                    painter = painterResource(id = theme.bgImage),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds

                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                uiModel.temp?.let { temp ->
                    Text(
                        text = temp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = 60.sp, fontFamily = amentFamily)
                    )
                }
                uiModel.condition?.let {
                    Text(
                        text = uiModel.condition,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        style = TextStyle(fontSize = 50.sp, fontFamily = amentFamily)
                    )
                }
            }


        }
        uiModel.theme?.let {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(color = it.color)
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .background(SUNNY)
                    .fillMaxSize()
                    .background(color = it.color)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentHeight()
                                .align(Alignment.Center),
                            verticalArrangement = Arrangement.Center
                        ) {
                            uiModel.tempMin?.let { tempMin ->
                                Text(
                                    fontFamily = amentFamily,
                                    text = tempMin,
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
                                )
                            }
                            Text(fontFamily = amentFamily, text = "min", textAlign = TextAlign.Center, color = Color.White)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentHeight()
                                .align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            uiModel.temp?.let { temp ->
                                Text(fontFamily = amentFamily, text = temp, textAlign = TextAlign.Center, color = Color.White)
                            }
                            Text(
                                fontFamily = amentFamily,
                                text = "Current",
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentHeight()
                                .align(Alignment.Center),

                            horizontalAlignment = Alignment.End
                        ) {
                            uiModel.tempMax?.let { tempMax ->
                                Box(
                                    modifier = Modifier.wrapContentHeight()

                                ) {
                                    Text(
                                        fontFamily = amentFamily,
                                        text = tempMax,
                                        textAlign = TextAlign.Center,
                                        color = Color.White,
                                        modifier = Modifier.wrapContentHeight()

                                    )
                                }
                            }
                            Text(fontFamily = amentFamily, text = "max", textAlign = TextAlign.Center, color = Color.White)
                        }
                    }
                }
            }
        }

        Divider(
            color = Color.White,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )
    }
}

