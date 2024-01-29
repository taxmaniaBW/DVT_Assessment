package com.utick.dvtcodingassessment.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.utick.dvtcodingassessment.ui.data.Content
import com.utick.dvtcodingassessment.ui.util.amentFamily
import shimmerLoadingAnimation

@Composable
fun ForecastWeatherLoading() {
    Column(modifier = Modifier
        .background(color = Color.White)
        .fillMaxSize()) {
        Spacer(modifier = Modifier.height(48.dp))
        LazyColumn {
            items(6) { DayItemLoading()
            }
        }
    }
}

@Composable
fun DayItemLoading() {

    Row(modifier = Modifier
        .height(40.dp)
        .fillMaxSize()
        .padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {


        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Color.LightGray)
                .size(height = 30.dp, width = 150.dp)
                .weight(1f)
                .shimmerLoadingAnimation()
        )



            Box(modifier = Modifier
                .weight(1f)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Color.LightGray)
                .size(height = 10.dp, width = 50.dp)
                .shimmerLoadingAnimation())


        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Color.LightGray)
                .size(height = 30.dp, width = 30.dp)
                .shimmerLoadingAnimation()
        )

    }

}