package com.example.weathermvvm.domain.repository

import com.example.weathermvvm.domain.model.WeatherResponse
import retrofit2.Response

interface GetWeatherSearch {
    suspend fun searchWeather(query: String): Response<WeatherResponse>
}