package com.example.weathermvvm.domain.model.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Wind(
    @SerializedName("speed")
    val speed: Double
)