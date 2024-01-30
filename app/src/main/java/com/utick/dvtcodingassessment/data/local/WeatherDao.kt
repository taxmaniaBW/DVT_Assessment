package com.utick.dvtcodingassessment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(currentWeatherEntity: CurrentWeatherData)

    @Query("SELECT * FROM current_weather")
    fun getCurrentWeather(): CurrentWeatherData

}