package com.thitiphat.data.forecast.api

import com.thitiphat.data.forecast.model.ForecastResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("data/2.5/forecast")
    fun getForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") key: String = "51548ca56c844c6f8717df65732260dc",
        @Query("units") unit: String = "imperial"
    ): Single<Response<ForecastResponseModel>>

}