package com.example.weathermvvm.domain.model.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class WeatherSearchResponse(
    @SerializedName("city")
    val city: City,
    @SerializedName("list")
    val list: List<CommonInfo>
)