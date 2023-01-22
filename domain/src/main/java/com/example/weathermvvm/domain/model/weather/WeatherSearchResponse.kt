package com.example.weathermvvm.domain.model.weather

data class WeatherSearchResponse(
    val city: City,
    val list: List<CommonInfo>
)