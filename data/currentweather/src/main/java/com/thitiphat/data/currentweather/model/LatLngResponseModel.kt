package com.thitiphat.data.currentweather.model

import com.google.gson.annotations.SerializedName

data class LatLngResponseModel(
    @SerializedName("list") val list: List<LatLngModel>? = null,
)

data class LatLngModel(
    @SerializedName("name") val name: String? = null,
    @SerializedName("lat") val lat: String? = null,
    @SerializedName("lon") val lng: String? = null,
    @SerializedName("country") val country: String? = null,
)
