package com.utick.dvtcodingassessment.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.utick.dvtcodingassessment.ui.data.Content
import com.utick.dvtcodingassessment.ui.util.amentFamily


@Composable
fun DayItem(day: Content) {

    Row(modifier = Modifier.height(40.dp).fillMaxSize().padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

        day.day?.let {
            Box(modifier = Modifier.weight(1f)) {
                Text(fontFamily = amentFamily, text = day.day)
            }

        }
        day.icon?.let { icon ->
            Box(modifier = Modifier.weight(1f)) {
                Image(
                    painterResource(icon),
                    contentDescription = "",
                )
            }
        }
        day.temp?.let { temp ->
            Box(modifier = Modifier) {
                Text(fontFamily = amentFamily, text = temp)
            }
        }
    }

}
