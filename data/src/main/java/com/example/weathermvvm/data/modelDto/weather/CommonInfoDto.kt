package com.example.weathermvvm.data.modelDto.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class CommonInfoDto(
    @SerializedName("clouds")
    val clouds: CloudsDto,
    @SerializedName("dt_txt")
    val dt_txt: String,
    @SerializedName("main")
    val main: MainDto,
    @SerializedName("weather")
    val weather: List<WeatherDto>,
    @SerializedName("wind")
    val wind: WindDto
)