package com.thitiphat.currentweather.di

import com.thitiphat.currentweather.presentation.CurrentWeatherViewModel
import com.thitiphat.domain.currentweather.GetCurrentWeatherUseCase
import com.thitiphat.data.currentweather.repository.Repository
import com.thitiphat.data.currentweather.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class CurrentWeatherKoinModel {

    private val apiModule = module {
        single { CurrentWeatherApiModule.createApi(get()) }
    }

    private val repositoryModule = module {
        factory<Repository> {
            RepositoryImpl(get())
        }
    }

    private val viewModelModule = module {
        viewModel { CurrentWeatherViewModel(get(), get()) }
    }

    private val useCaseModule = module {
        factory { GetCurrentWeatherUseCase(get()) }
    }

    val koinModules =
        module {
            includes(apiModule, repositoryModule, viewModelModule, useCaseModule)
        }
}