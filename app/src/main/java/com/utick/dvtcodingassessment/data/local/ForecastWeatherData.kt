package com.utick.dvtcodingassessment.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast_weather")
class ForecastWeatherData(
    @ColumnInfo(name = "temp") val temp: String?,
    @PrimaryKey
    @ColumnInfo(name = "day") val day: String,
    @ColumnInfo(name = "condition") val condition: String?)