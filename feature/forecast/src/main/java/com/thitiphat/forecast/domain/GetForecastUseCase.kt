package com.thitiphat.forecast.domain

import com.thitiphat.forecast.data.model.ForecastResponseModel
import com.thitiphat.forecast.data.repository.Repository

class GetForecastUseCase(private val repository: Repository) {

    fun invoke(lat: String, lng: String, callback: (ForecastResponseModel) -> Unit) {
        repository.getForecast(lat, lng).subscribe({ response ->
            response.body()?.let { responseBody ->
                callback.invoke(responseBody)
            }
        }, {

        })
    }
}