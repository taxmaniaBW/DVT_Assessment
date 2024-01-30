package com.utick.dvtcodingassessment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CurrentWeatherData::class, ForecastWeatherData::class), version = 3, exportSchema = false)
abstract class WeatherDB : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}
