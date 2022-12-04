package com.thitiphat.search.di

import com.thitiphat.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class SearchKoinModule {

    private val viewModelModule = module {
        viewModel { SearchViewModel(get(), get()) }
    }

    val koinModules =
        module {
            includes(viewModelModule)
        }
}