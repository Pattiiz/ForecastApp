package com.thitiphat.search.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thitiphat.domain.currentweather.GetCurrentWeatherUseCase
import com.thitiphat.data.currentweather.model.CurrentWeatherResponseModel
import com.thitiphat.domain.forecast.GetForecastUseCase
import com.thitiphat.data.forecast.model.ForecastResponseModel

class SearchViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeatherResponseModel>()
    val currentWeather: LiveData<CurrentWeatherResponseModel> = _currentWeather
    private val _forecast = MutableLiveData<ForecastResponseModel>()
    val forecast: LiveData<ForecastResponseModel> = _forecast

    private val originalData = listOf("Bangkok", "London", "Tokyo", "Kyoto", "Seoul", "Stockholm", "Singapore", "Chiang Mai")
    val filteredData = mutableStateOf(originalData)

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

    fun filterListOfItem(query: String) {
        if (query.isEmpty()) {
            filteredData.value = originalData
            return
        }
        filteredData.value = originalData.filter {
            it.lowercase().contains(query.lowercase())
        }

    }

}