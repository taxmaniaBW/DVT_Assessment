package com.utick.dvtcodingassessment

import android.app.Application
import com.utick.dvtcodingassessment.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class DVTApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@DVTApplication)
            modules(appModule)
        }

    }
}