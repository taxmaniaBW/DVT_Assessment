package com.utick.dvtcodingassessment.di

import com.utick.dvtcodingassessment.ui.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { WeatherViewModel(get(), get()) }
}