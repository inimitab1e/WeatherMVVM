package com.example.weathermvvm.domain.model.weather

data class CommonInfo(
    val clouds: Clouds,
    val dt_txt: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)