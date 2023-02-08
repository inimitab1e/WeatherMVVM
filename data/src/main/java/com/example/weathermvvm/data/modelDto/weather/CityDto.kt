package com.example.weathermvvm.data.modelDto.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class CityDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val coord: CoordDto
)