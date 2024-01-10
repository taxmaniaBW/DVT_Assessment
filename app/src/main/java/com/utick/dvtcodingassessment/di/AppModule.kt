package com.utick.dvtcodingassessment.di


import com.utick.dvtcodingassessment.network.ApiClient
import com.utick.dvtcodingassessment.util.NetworkHandler
import org.koin.dsl.module

val appModule = module {
    includes(repositoryModule)
    single{::NetworkHandler}
    single { ::ApiClient }
}