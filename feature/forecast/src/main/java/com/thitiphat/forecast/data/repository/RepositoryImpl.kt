package com.thitiphat.forecast.data.repository


import com.thitiphat.forecast.data.api.ForecastApi
import com.thitiphat.forecast.data.model.LatLngResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class RepositoryImp(val forecastApi: ForecastApi) : Repository {

    override fun getLatLng(): Single<Response<LatLngResponseModel>> {
        return Single.just(
            Response.success(
                LatLngResponseModel(
                    zip = "123",
                    name = "1234",
                    lat = "12345",
                    lng = "123456",
                    country = "TH"
                )
            )
        )
    }

}