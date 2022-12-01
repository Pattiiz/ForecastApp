package com.thitiphat.forecast.di

import com.thitiphat.forecast.data.api.ForecastApi
import retrofit2.Retrofit

object ForecastApiModule {

    fun createApi(retrofit: Retrofit): ForecastApi {
        return retrofit.create(ForecastApi::class.java)
    }

}