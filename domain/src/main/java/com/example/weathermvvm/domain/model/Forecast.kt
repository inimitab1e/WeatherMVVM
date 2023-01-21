package com.example.weathermvvm.domain.model

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Forecast(
    @SerializedName("forecastday")
    val forecastday: List<Forecastday>
)