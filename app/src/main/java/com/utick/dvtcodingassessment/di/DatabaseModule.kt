package com.utick.dvtcodingassessment.di

import androidx.room.Room
import com.utick.dvtcodingassessment.data.local.WeatherDB
import com.utick.dvtcodingassessment.data.local.WeatherDao
import com.utick.dvtcodingassessment.util.DB_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            WeatherDB::class.java,
            DB_NAME,
        ).fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<WeatherDB>().weatherDao()
    }
}
