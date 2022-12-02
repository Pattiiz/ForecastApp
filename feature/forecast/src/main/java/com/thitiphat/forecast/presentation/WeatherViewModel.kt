package com.thitiphat.forecast.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thitiphat.forecast.data.model.CurrentWeatherResponseModel
import com.thitiphat.forecast.data.model.ForecastResponseModel
import com.thitiphat.forecast.domain.GetCurrentWeatherUseCase
import com.thitiphat.forecast.domain.GetForecastUseCase

class WeatherViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase
) :
    ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeatherResponseModel>()
    val currentWeather: LiveData<CurrentWeatherResponseModel> = _currentWeather
    private val _forecast = MutableLiveData<ForecastResponseModel>()
    val forecast: LiveData<ForecastResponseModel> = _forecast

    fun getCurrentWeather(city: String) {
        getCurrentWeatherUseCase.invoke(city = city) {
            _currentWeather.value = it
        }
    }

    fun getForecast(lat: String, lng: String) {
        getForecastUseCase.invoke(lat, lng) {
            _forecast.value = it
        }
    }

}