package com.utick.dvtcodingassessment.di


import com.utick.dvtcodingassessment.DVTApplication
import com.utick.dvtcodingassessment.MainActivity
import com.utick.dvtcodingassessment.data.apiService.GetCurrentWeather
import com.utick.dvtcodingassessment.data.apiService.GetFiveDayForecast
import com.utick.dvtcodingassessment.network.ApiClient
import com.utick.dvtcodingassessment.util.NetworkHandler
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    includes(repositoryModule)
    includes(viewModelsModule)
    single(named("IODispatcher")) {
        Dispatchers.IO
    }
    single{::NetworkHandler}
    single { ApiClient(CIO.create()) }
    single { GetCurrentWeather(get(), get(), get(named("IODispatcher"))) }
}