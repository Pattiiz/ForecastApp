package com.thitiphat.data.currentweather.repository

import com.thitiphat.data.currentweather.model.CurrentWeatherResponseModel
import com.thitiphat.data.currentweather.model.LatLngModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface CurrentWeatherRepository {

    fun getLatLng(cityName: String): Single<Response<List<LatLngModel>>>
    fun getCurrentWeather(lat: String, lon: String): Single<Response<CurrentWeatherResponseModel>>

}