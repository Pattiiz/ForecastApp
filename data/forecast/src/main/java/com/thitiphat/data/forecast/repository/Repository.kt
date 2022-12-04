package com.thitiphat.data.forecast.repository

import com.thitiphat.data.forecast.model.ForecastResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface Repository {

    fun getForecast(lat: String, lon: String): Single<Response<ForecastResponseModel>>

}