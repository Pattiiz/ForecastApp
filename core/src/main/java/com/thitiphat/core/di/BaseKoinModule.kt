package com.thitiphat.core.di

import com.thitiphat.core.retrofit.RetrofitModule
import org.koin.dsl.module

class BaseKoinModule {

    private val networkModule = module {
        single { RetrofitModule.init() }
    }

    val koinModules = module { includes(networkModule) }

}