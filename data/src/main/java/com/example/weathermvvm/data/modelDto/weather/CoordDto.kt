package com.example.weathermvvm.data.modelDto.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class CoordDto(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)
