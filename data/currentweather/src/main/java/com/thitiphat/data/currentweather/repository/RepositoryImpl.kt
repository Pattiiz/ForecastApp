package com.thitiphat.data.currentweather.repository

import com.thitiphat.data.currentweather.api.CurrentWeatherApi
import com.thitiphat.data.currentweather.model.CurrentWeatherResponseModel
import com.thitiphat.data.currentweather.model.LatLngModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class RepositoryImpl(private val api: CurrentWeatherApi) : Repository {

    override fun getLatLng(cityName: String): Single<Response<List<LatLngModel>>> {
        return api.getLatLng(cityName).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCurrentWeather(
        lat: String,
        lon: String
    ): Single<Response<CurrentWeatherResponseModel>> {
        return api.getCurrentWeather(lat, lon).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}