package com.utick.dvtcodingassessment.di


import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.apiService.GetFiveDayForecast
import com.utick.dvtcodingassessment.network.ApiClient
import com.utick.dvtcodingassessment.ui.util.LocationHelper
import com.utick.dvtcodingassessment.ui.view.HomeView
import com.utick.dvtcodingassessment.util.NetworkHandler
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    includes(repositoryModule)
    includes(viewModelsModule)
    single(named("IODispatcher")) {
        Dispatchers.IO
    }
    single{NetworkHandler(androidContext())}
    single { ApiClient(CIO.create()) }
    single { GetCurrentWeather(get(), get(), get(named("IODispatcher"))) }
    single { GetFiveDayForecast(get(), get(), get(named("IODispatcher"))) }
    single { HomeView() }
}