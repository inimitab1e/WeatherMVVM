package com.example.weathermvvm.domain.model.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Main(
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("temp_max")
    val temp_max: Double,
    @SerializedName("temp_min")
    val temp_min: Double
)