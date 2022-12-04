package com.thitiphat.data.currentweather.api

import com.thitiphat.data.currentweather.model.CurrentWeatherResponseModel
import com.thitiphat.data.currentweather.model.LatLngModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {

    @GET("geo/1.0/direct")
    fun getLatLng(
        @Query("q") cityName: String,
        @Query("limit") limit: String = "1",
        @Query("appid") key: String = "51548ca56c844c6f8717df65732260dc"
    ): Single<Response<List<LatLngModel>>>

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") key: String = "51548ca56c844c6f8717df65732260dc",
        @Query("units") unit: String = "imperial"
    ): Single<Response<CurrentWeatherResponseModel>>

}