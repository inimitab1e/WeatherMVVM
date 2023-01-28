package com.example.weathermvvm.data.repository

import com.example.data.BuildConfig
import com.example.weathermvvm.domain.repository.GetWeatherSearch
import com.example.weathermvvm.data.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWeatherSearchImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiKey: String = BuildConfig.api_key,
    private val getCoordsUrl: String = BuildConfig.base_url_coordinates,
    private val getWeatherSearchUrl: String = BuildConfig.base_url_weather,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GetWeatherSearch {
    override suspend fun getCoordinatesByName(
        locationName: String
    ) = withContext(dispatcher) {
        apiService.getCoordinates(
            url = getCoordsUrl,
            locationName = locationName,
            apiKey = apiKey
        )
    }

    override suspend fun searchWeather(
        latitude: Double,
        longitude: Double
    ) = withContext(dispatcher) {
        apiService.getWeatherSearch(
            url = getWeatherSearchUrl,
            latitude = latitude,
            longitude = longitude,
            apiKey = apiKey
        )
    }
}