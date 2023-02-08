package com.example.weathermvvm.domain.repository

import com.example.weathermvvm.domain.model.coordinates.LocationCoordsResponse
import com.example.weathermvvm.domain.network_features.result.Result
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse

interface GetWeatherSearch {

    suspend fun getCoordinatesByName(
        locationName: String
    ): Result<LocationCoordsResponse>

    suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double
    ): Result<WeatherSearchResponse?>
}