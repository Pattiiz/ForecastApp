package com.thitiphat.data.forecast.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastResponseModel(
    @SerializedName("list") val list: List<ForecastModel>
) : Parcelable

@Parcelize
data class ForecastModel(
    @SerializedName("dt") val dt: Int?,
    @SerializedName("main") val main: MainTempModel?,
    @SerializedName("weather") val list: List<WeatherModel>?,
    @SerializedName("dt_txt") val dtText: String?,
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

@Parcelize
data class WeatherModel(
    @SerializedName("main") val main: String?,
    @SerializedName("description") val description: String?
) : Parcelable
