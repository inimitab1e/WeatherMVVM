package com.example.weathermvvm.data.modelDto.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class CloudsDto(
    @SerializedName("all")
    val all: Int
)