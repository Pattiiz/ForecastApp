package com.thitiphat.data.currentweather.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeatherResponseModel(
    @SerializedName("coord") val coord: CoordModel?,
    @SerializedName("weather") val weather: List<WeatherModel>?,
    @SerializedName("base") val base: String?,
    @SerializedName("main") val main: MainTempModel?,
    @SerializedName("dt") val dt: Int?,
    @SerializedName("timezone") val timezone: Int?,
    @SerializedName("name") val name: String?
) : Parcelable

@Parcelize
data class CoordModel(
    @SerializedName("lon") val lon: Float?,
    @SerializedName("lat") val lat: Float?
) : Parcelable

@Parcelize
data class WeatherModel(
    @SerializedName("main") val main: String?,
    @SerializedName("description") val description: String?
) : Parcelable

@Parcelize
data class MainTempModel(
    @SerializedName("temp") val temp: Float?,
    @SerializedName("feels_like") val feels_like: Float?,
    @SerializedName("temp_min") val temp_min: Float?,
    @SerializedName("temp_max") val temp_max: Float?,
    @SerializedName("pressure") val pressure: Int?,
    @SerializedName("humidity") val humidity: Int?
) : Parcelable
