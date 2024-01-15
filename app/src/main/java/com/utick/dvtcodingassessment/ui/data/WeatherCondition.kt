package com.utick.dvtcodingassessment.ui.data

enum class WeatherCondition(val value: String) {
    SUNNY("Clear"),
    CLOUDY("Clouds"),
    RAINY("Rain"),
    SNOW("Snow");

    companion object {
        public fun fromValue(value: String): WeatherCondition = when (value) {
            "Clear" -> SUNNY
            "Clouds"  -> CLOUDY
            "Rain"  -> RAINY
            "Snow"  -> SNOW
            else         -> throw IllegalArgumentException()
        }
    }
}