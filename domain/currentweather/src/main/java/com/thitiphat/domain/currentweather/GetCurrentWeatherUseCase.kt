package com.thitiphat.domain.currentweather

import android.util.Log
import com.thitiphat.data.currentweather.model.CurrentWeatherResponseModel
import com.thitiphat.data.currentweather.repository.CurrentWeatherRepository

class GetCurrentWeatherUseCase(private val currentWeatherRepository: CurrentWeatherRepository) {

    private lateinit var callback: (CurrentWeatherResponseModel) -> Unit

    fun invoke(city: String, onSuccess: (CurrentWeatherResponseModel) -> Unit) {
        callback = onSuccess
        getLatLngFromCityName(city)
    }

    private fun getLatLngFromCityName(city: String) {
        currentWeatherRepository.getLatLng(city).subscribe({ response ->
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
        currentWeatherRepository.getCurrentWeather(lat, lng).subscribe({ response ->
            response.body()?.let {
                callback.invoke(it)
            }
        }, {

        })
    }

}