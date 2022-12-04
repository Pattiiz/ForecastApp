package com.thitiphat.currentweather.di

import com.thitiphat.data.currentweather.api.CurrentWeatherApi
import retrofit2.Retrofit

object CurrentWeatherApiModule {

    fun createApi(retrofit: Retrofit): CurrentWeatherApi {
        return retrofit.create(CurrentWeatherApi::class.java)
    }

}