package com.example.weathermvvm.domain.repository

import com.example.weathermvvm.domain.model.ErrorResponse
import com.example.weathermvvm.domain.model.coordinates.LocationCoordsResponse
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse
import com.example.weathermvvm.domain.network_features.NetworkResponse

interface GetWeatherSearch {

    suspend fun getCoordinatesByName(
        locationName: String
    ): NetworkResponse<LocationCoordsResponse, ErrorResponse>

    suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double
    ): NetworkResponse<WeatherSearchResponse, ErrorResponse>
}