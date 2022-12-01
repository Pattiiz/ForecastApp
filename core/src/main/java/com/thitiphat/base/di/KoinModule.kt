package com.thitiphat.base.di

import com.thitiphat.base.retrofit.RetrofitModule
import org.koin.dsl.module

class KoinModule {

    val baseModule = module {
        single { RetrofitModule.init() }
    }
}