package com.example.weathermvvm.domain.repository

import com.example.weathermvvm.domain.model.coordinates.LocationCoordsResponse
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse
import retrofit2.Call

interface GetWeatherSearch {
    suspend fun getCoordinatesByName(
        url: String,
        locationName: String,
        apiKey: String
    ): Call<LocationCoordsResponse>

    suspend fun searchWeather(
        url: String,
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): Call<WeatherSearchResponse>
}