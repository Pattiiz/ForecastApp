package com.thitiphat.currentweather.di

import com.thitiphat.data.currentweather.api.CurrentWeatherApi
import retrofit2.Retrofit

object CurrentWeatherApiModule {

    fun createApi(retrofit: Retrofit): com.thitiphat.data.currentweather.api.CurrentWeatherApi {
        return retrofit.create(com.thitiphat.data.currentweather.api.CurrentWeatherApi::class.java)
    }

}