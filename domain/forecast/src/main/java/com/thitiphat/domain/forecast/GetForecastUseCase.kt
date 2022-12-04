package com.thitiphat.domain.forecast

import com.thitiphat.data.forecast.model.ForecastResponseModel
import com.thitiphat.data.forecast.repository.Repository

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