package com.thitiphat.krungsritest

import android.app.Application
import com.thitiphat.base.di.KoinModule
import org.koin.core.context.startKoin

class KrungsriTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(KoinModule().baseModule)
        }
    }

}