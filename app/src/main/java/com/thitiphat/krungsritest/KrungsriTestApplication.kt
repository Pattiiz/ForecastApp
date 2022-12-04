package com.thitiphat.krungsritest

import android.app.Application
import com.thitiphat.core.di.BaseKoinModule
import com.thitiphat.currentweather.di.CurrentWeatherKoinModel
import com.thitiphat.forecast.di.ForecastKoinModule
import com.thitiphat.search.di.SearchKoinModule
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
                    CurrentWeatherKoinModel().koinModules,
                    ForecastKoinModule().koinModules,
                    SearchKoinModule().koinModules
                )
            )
        }
    }

}