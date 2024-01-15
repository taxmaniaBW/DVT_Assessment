package com.utick.dvtcodingassessment.di

import com.utick.dvtcodingassessment.data.repository.WeatherRepository
import com.utick.dvtcodingassessment.data.repository.WeatherRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}