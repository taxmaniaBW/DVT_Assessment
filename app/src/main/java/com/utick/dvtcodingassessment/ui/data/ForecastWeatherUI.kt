package com.utick.dvtcodingassessment.ui.data

import com.utick.dvtcodingassessment.util.Failure

data class ForecastWeatherUI(
    var loading: Boolean = false,
    val hasError: Boolean = false,
    val error: Failure = Failure.None,
    val content: List<Content> = emptyList()

    )

data class Content(
    val temp: String? = null,
    val day: String? = null,
    val icon: Int? = null,
)