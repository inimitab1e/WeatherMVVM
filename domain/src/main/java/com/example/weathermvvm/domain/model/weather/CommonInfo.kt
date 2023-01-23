package com.example.weathermvvm.domain.model.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class CommonInfo(
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("dt_txt")
    val dt_txt: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
)