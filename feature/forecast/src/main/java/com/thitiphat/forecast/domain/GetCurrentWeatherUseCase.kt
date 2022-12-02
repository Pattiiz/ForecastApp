package com.thitiphat.forecast.domain

import android.util.Log
import com.thitiphat.forecast.data.model.CurrentWeatherResponseModel
import com.thitiphat.forecast.data.repository.Repository

class GetCurrentWeatherUseCase(private val repository: Repository) {

    private lateinit var callback: (CurrentWeatherResponseModel) -> Unit

    fun invoke(city: String, onSuccess: (CurrentWeatherResponseModel) -> Unit) {
        callback = onSuccess
        getLatLngFromCityName(city)
    }

    private fun getLatLngFromCityName(city: String) {
        repository.getLatLng(city).subscribe({ response ->
            response.body()?.let { responseBody ->
                getCurrentWeather(
                    responseBody.first().lat.orEmpty(),
                    responseBody.first().lng.orEmpty()
                )
            }
        }, {
            Log.e("THROW", it.message.toString())
        })
    }

    private fun getCurrentWeather(lat: String, lng: String) {
        repository.getCurrentWeather(lat, lng).subscribe({ response ->
            response.body()?.let {
                callback.invoke(it)
            }
        }, {

        })
    }

}