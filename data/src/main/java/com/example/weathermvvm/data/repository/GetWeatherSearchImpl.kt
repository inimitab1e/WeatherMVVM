package com.example.weathermvvm.data.repository

import com.example.weathermvvm.domain.repository.GetWeatherSearch
import com.example.weathermvvm.data.network.ApiService
import javax.inject.Inject

class GetWeatherSearchImpl @Inject constructor(
    private val apiService: ApiService
) : GetWeatherSearch {
    override suspend fun getCoordinatesByName(
        url: String,
        locationName: String,
        apiKey: String
    ) = apiService.getCoordinates(
        url = url,
        locationName = locationName,
        apiKey = apiKey
    )

    override suspend fun searchWeather(
        url: String,
        latitude: Double,
        longitude: Double,
        apiKey: String
    ) = apiService.getWeatherSearch(
        url = url,
        latitude = latitude,
        longitude = longitude,
        apiKey = apiKey
    )
}