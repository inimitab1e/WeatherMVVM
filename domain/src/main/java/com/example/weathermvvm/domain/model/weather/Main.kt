package com.example.weathermvvm.domain.model.weather

data class Main(
    val pressure: Int,
    val temp_max: Double,
    val temp_min: Double,
    val humidity: Int
)