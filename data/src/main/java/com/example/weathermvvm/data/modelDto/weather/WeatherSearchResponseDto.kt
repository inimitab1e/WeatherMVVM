package com.example.weathermvvm.data.modelDto.weather

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class WeatherSearchResponseDto(
    @SerializedName("city")
    val city: CityDto,
    @SerializedName("list")
    val list: List<CommonInfoDto>
)