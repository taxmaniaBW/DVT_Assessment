package com.utick.dvtcodingassessment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.utick.dvtcodingassessment.ui.theme.SUNNY
import shimmerLoadingAnimation

@Composable
fun CurrentWeatherLoading() {
    Column {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color.White)


        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(24.dp))
                        .background(color = Color.LightGray)
                        .size(100.dp)
                        .shimmerLoadingAnimation()
                )
                Spacer(modifier = Modifier.padding(24.dp))
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.LightGray)
                        .size(height = 30.dp, width = 150.dp)
                        .shimmerLoadingAnimation()
                )
            }


        }
        Box(
            modifier = Modifier
                .weight(0.2f)
                .background(SUNNY)
                .fillMaxSize()
                .background(color = Color.LightGray)
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

                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(24.dp))
                                .background(color = Color.DarkGray)
                                .size(30.dp)
                                .wrapContentHeight(align = Alignment.CenterVertically)
                                .shimmerLoadingAnimation()
                        )

                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = Color.DarkGray)
                                .size(height = 10.dp, width = 50.dp)
                                .shimmerLoadingAnimation()
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
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(24.dp))
                                .background(color = Color.DarkGray)
                                .size(30.dp)
                                .shimmerLoadingAnimation()
                        )

                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = Color.DarkGray)
                                .size(height = 10.dp, width = 50.dp)
                                .shimmerLoadingAnimation()
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

                            Box(
                                modifier = Modifier.wrapContentHeight()

                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(24.dp))
                                        .background(color = Color.DarkGray)
                                        .size(30.dp)
                                        .wrapContentHeight(align = Alignment.CenterVertically)
                                        .shimmerLoadingAnimation()
                                )
                            }

                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = Color.DarkGray)
                                .size(height = 10.dp, width = 50.dp)
                                .shimmerLoadingAnimation()
                        )
                    }
                }
            }
        }
    }
}
