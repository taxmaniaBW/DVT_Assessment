package com.utick.dvtcodingassessment.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
class CurrentWeatherData(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    @ColumnInfo(name = "temp") val temp: String?,
    @ColumnInfo(name = "tempMin") val tempMin: String?,
    @ColumnInfo(name = "tempMax") val tempMax: String?,
    @ColumnInfo(name = "condition") val condition: String?,
    @ColumnInfo(name = "updatedDate") val updatedDate: Long?,

    )