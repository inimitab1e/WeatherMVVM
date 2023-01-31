package com.example.weathermvvm.domain.repository

import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse

interface GetWeatherSearch {

    suspend fun searchWeather(
        locationName: String
    ): WeatherSearchResponse?
}