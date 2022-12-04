package com.thitiphat.domain.forecast

import com.thitiphat.data.forecast.model.ForecastResponseModel
import com.thitiphat.data.forecast.repository.ForecastRepository

class GetForecastUseCase(private val forecastRepository: ForecastRepository) {

    fun invoke(lat: String, lng: String, callback: (ForecastResponseModel) -> Unit) {
        forecastRepository.getForecast(lat, lng).subscribe({ response ->
            response.body()?.let { responseBody ->
                callback.invoke(responseBody)
            }
        }, {

        })
    }
}