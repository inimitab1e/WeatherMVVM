package com.example.weathermvvm.data.modelDto.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class MainDto(
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("temp_max")
    val temp_max: Double,
    @SerializedName("temp_min")
    val temp_min: Double,
    @SerializedName("himidity")
    val humidity: Int
)