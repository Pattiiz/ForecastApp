package com.thitiphat.base.di

import com.thitiphat.base.retrofit.RetrofitModule
import org.koin.dsl.module

class KoinModule {

    private val apiModule = module {
        single { RetrofitModule.init() }
    }

    val baseModule = listOf(apiModule)

}