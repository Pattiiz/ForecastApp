package com.thitiphat.forecast.data.repository

import com.thitiphat.forecast.data.model.LatLngResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface Repository {

    fun getLatLng(): Single<Response<LatLngResponseModel>>
}