package com.utick.dvtcodingassessment.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.utick.dvtcodingassessment.R
import com.utick.dvtcodingassessment.data.response.forecastresponse.Day
import com.utick.dvtcodingassessment.ui.model.DayRowModel
import com.utick.dvtcodingassessment.util.getDayOfWeek


@Composable
fun DayRow(day: DayRowModel) {

    Column {
        Row {
            Modifier
                .weight(1f)
            Text(text = day.day)
        }
        Row {
            Modifier
                .weight(1f)
            Image(
                painterResource(day.weatherIcon),
                contentDescription = "",
                modifier = Modifier.fillMaxSize())
        }
        Row {
            Modifier
                .weight(1f)
            Text(text = day.maxTemp.toString())
        }
    }
}
