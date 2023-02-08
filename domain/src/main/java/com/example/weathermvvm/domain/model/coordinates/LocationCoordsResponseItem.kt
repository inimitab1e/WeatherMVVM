package com.example.weathermvvm.domain.model.coordinates

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class LocationCoordsResponseItem(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String
)