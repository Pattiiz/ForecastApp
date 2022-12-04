package com.thitiphat.forecast.di

import com.thitiphat.domain.forecast.GetForecastUseCase
import com.thitiphat.data.forecast.repository.ForecastRepository
import com.thitiphat.data.forecast.repository.ForecastRepositoryImpl
import org.koin.dsl.module

class ForecastKoinModule {

    private val apiModule = module {
        single { ForecastApiModule.createApi(get()) }
    }

    private val repositoryModule = module {
        factory<ForecastRepository> {
            ForecastRepositoryImpl(
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