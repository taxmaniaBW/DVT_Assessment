package com.utick.dvtcodingassessment.data.response.forecastresponse

import com.utick.dvtcodingassessment.data.local.ForecastWeatherData
import com.utick.dvtcodingassessment.ui.data.Content
import com.utick.dvtcodingassessment.util.asTemperatureString
import com.utick.dvtcodingassessment.util.getDayOfWeek
import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeatherResponse (
    val cod: String? = null,
    val message: Long? = null,
    val cnt: Long? = null,
    val list: List<Day>,
    val city: City? = null
)

fun ForecastWeatherResponse.minimized(): List<ForecastWeatherData> {
    val forecastWeatherDataList = arrayListOf<ForecastWeatherData>()
    val byGroup = list.groupBy { getDayOfWeek(it.dt) }
    byGroup.entries.forEach {

        forecastWeatherDataList.add(
            ForecastWeatherData(
                temp = it.value[0].main.temp.asTemperatureString(),
                day = it.key,
                condition = it.value[0].weather[0].main,
            )
        )
    }
    return forecastWeatherDataList
}