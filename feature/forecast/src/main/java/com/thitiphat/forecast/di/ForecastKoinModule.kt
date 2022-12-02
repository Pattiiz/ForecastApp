package com.thitiphat.forecast.di

import com.thitiphat.forecast.data.repository.Repository
import com.thitiphat.forecast.data.repository.RepositoryImp
import com.thitiphat.forecast.domain.GetCurrentWeatherUseCase
import com.thitiphat.forecast.domain.GetForecastUseCase
import com.thitiphat.forecast.presentation.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ForecastKoinModule {

    private val apiModule = module {
        single { ForecastApiModule.createApi(get()) }
    }

    private val repositoryModule = module {
        factory<Repository> { RepositoryImp(get()) }
    }

    private val viewModelModule = module {
        viewModel { WeatherViewModel(get(), get()) }
    }

    private val useCaseModule = module {
        factory { GetCurrentWeatherUseCase(get()) }
        factory { GetForecastUseCase(get()) }
    }

    val koinModules =
        module {
            includes(apiModule, repositoryModule, viewModelModule, useCaseModule)
        }
}