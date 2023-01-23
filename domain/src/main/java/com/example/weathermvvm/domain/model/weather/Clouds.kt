package com.example.weathermvvm.domain.model.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Clouds(
    @SerializedName("all")
    val all: Int
)