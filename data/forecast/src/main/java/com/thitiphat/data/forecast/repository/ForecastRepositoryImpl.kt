package com.thitiphat.data.forecast.repository


import com.thitiphat.data.forecast.api.ForecastApi
import com.thitiphat.data.forecast.model.ForecastResponseModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class ForecastRepositoryImpl(private val forecastApi: ForecastApi) : ForecastRepository {

    override fun getForecast(lat: String, lon: String): Single<Response<ForecastResponseModel>> {
        return forecastApi.getForecast(lat, lon).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}