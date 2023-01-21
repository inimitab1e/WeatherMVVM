package com.example.weathermvvm.domain.model

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Forecastday(
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val day: Day
)