package com.thitiphat.krungsritest

import android.app.Application
import com.thitiphat.base.di.BaseKoinModule
import com.thitiphat.forecast.di.ForecastKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KrungsriTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KrungsriTestApplication)
            modules(
                listOf(
                    BaseKoinModule().koinModules,
                    ForecastKoinModule().koinModules
                )
            )
        }
    }

}