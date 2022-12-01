package com.thitiphat.forecast.di

import com.thitiphat.forecast.data.repository.Repository
import com.thitiphat.forecast.data.repository.RepositoryImp
import org.koin.dsl.module

class ForecastKoinModule {

    private val apiModule = module {
        single { ForecastApiModule.createApi(get()) }
    }

    private val repositoryModule = module {
        factory<Repository> { RepositoryImp(get()) }
    }

    val koinModules = module { includes(apiModule, repositoryModule)}
}