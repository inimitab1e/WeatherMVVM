package com.example.weathermvvm.domain.model

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class WeatherResponse(
    @SerializedName("forecast")
    val forecast: Forecast
)