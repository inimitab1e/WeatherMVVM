package com.example.weathermvvm.data.repository

import com.example.data.BuildConfig
import com.example.weathermvvm.domain.repository.GetWeatherSearch
import com.example.weathermvvm.data.network.ApiService
import com.example.weathermvvm.data.toWeatherSearchResponse
import com.example.weathermvvm.domain.network_features.result.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWeatherSearchImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiKey: String = BuildConfig.api_key,
    private val getCoordsUrl: String = BuildConfig.base_url_coordinates,
    private val getWeatherSearchUrl: String = BuildConfig.base_url_weather,
    private val ioDispatcher: CoroutineDispatcher
) : GetWeatherSearch {

    override suspend fun getCoordinatesByName(
        locationName: String
    ) = withContext(ioDispatcher) {
        val coordsResponse = apiService.getCoordinates(
            url = getCoordsUrl,
            locationName = locationName,
            apiKey = apiKey
        )
        return@withContext coordsResponse
    }

    override suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double
    ) = withContext(ioDispatcher) {
        val weatherResponse = apiService.getWeatherSearch(
            url = getWeatherSearchUrl,
            latitude = latitude,
            longitude = longitude,
            apiKey = apiKey
        )
        val weatherResponseBody = weatherResponse.map { value -> value.toWeatherSearchResponse() }
        return@withContext weatherResponseBody
    }
}