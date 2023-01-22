package com.example.weathermvvm.domain.repository

import com.example.weathermvvm.domain.model.coordinates.LocationCoordsResponse
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse
import retrofit2.Response

interface GetWeatherSearch {
    suspend fun getCoordinatesByName(
        locationName: String
    ): Response<LocationCoordsResponse>

    suspend fun searchWeather(
        latitude: Double,
        longitude: Double
    ): Response<WeatherSearchResponse>
}