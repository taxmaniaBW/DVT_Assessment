package com.utick.dvtcodingassessment.ui.data

import com.utick.dvtcodingassessment.util.Failure

data class CurrentWeatherUI(
    var loading: Boolean = false,
    val temp: String? = null,
    val condition: String? = null,
    val hasError: Boolean = false,
    val error: Failure = Failure.None,
    val theme: CurrentWeatherTheme? = null
    )