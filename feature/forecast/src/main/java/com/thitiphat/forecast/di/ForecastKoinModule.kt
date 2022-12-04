package com.thitiphat.forecast.di

import com.thitiphat.domain.forecast.GetForecastUseCase
import com.thitiphat.data.forecast.repository.Repository
import com.thitiphat.data.forecast.repository.RepositoryImpl
import org.koin.dsl.module

class ForecastKoinModule {

    private val apiModule = module {
        single { ForecastApiModule.createApi(get()) }
    }

    private val repositoryModule = module {
        factory<Repository> {
            RepositoryImpl(
                get()
            )
        }
    }

    private val useCaseModule = module {
        factory { GetForecastUseCase(get()) }
    }

    val koinModules =
        module {
            includes(apiModule, repositoryModule, useCaseModule)
        }
}