package com.example.weathermvvm.domain.model.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class City(
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val coord: Coord
)