package com.utick.dvtcodingassessment.di


import com.utick.dvtcodingassessment.network.ApiClient
import org.koin.dsl.module

val appModule = module {
    includes(repositoryModule)
}