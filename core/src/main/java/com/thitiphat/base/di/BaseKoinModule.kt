package com.thitiphat.base.di

import com.thitiphat.base.retrofit.RetrofitModule
import org.koin.dsl.module

class BaseKoinModule {

    private val networkModule = module {
        single { RetrofitModule.init() }
    }

    val koinModules = module { includes(networkModule) }

}