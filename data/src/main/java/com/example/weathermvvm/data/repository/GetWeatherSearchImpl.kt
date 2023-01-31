package com.example.weathermvvm.data.repository

import com.example.weathermvvm.domain.network_features.result.isSuccess
import com.example.weathermvvm.domain.network_features.result.asSuccess
import com.example.data.BuildConfig
import com.example.weathermvvm.data.AppDispatchers
import com.example.weathermvvm.domain.repository.GetWeatherSearch
import com.example.weathermvvm.data.network.ApiService
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWeatherSearchImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiKey: String = BuildConfig.api_key,
    private val getCoordsUrl: String = BuildConfig.base_url_coordinates,
    private val getWeatherSearchUrl: String = BuildConfig.base_url_weather,
    private val dispatchers: AppDispatchers
) : GetWeatherSearch {

    override suspend fun searchWeather(
        locationName: String
    ): WeatherSearchResponse? {
        return withContext(dispatchers.io) {
            val coordsResponse = apiService.getCoordinates(
                url = getCoordsUrl,
                locationName = locationName,
                apiKey = apiKey
            )
            if (coordsResponse.isSuccess()) {
                val latitude = coordsResponse.asSuccess().value[0].lat
                val longitude = coordsResponse.asSuccess().value[0].lon
                val weatherResponse = apiService.getWeatherSearch(
                    url = getWeatherSearchUrl,
                    latitude = latitude,
                    longitude = longitude,
                    apiKey = apiKey
                )
                if (weatherResponse.isSuccess()) {
                    weatherResponse.asSuccess().value
                } else {
                    null
                }
            } else {
                null
            }
        }
    }
}