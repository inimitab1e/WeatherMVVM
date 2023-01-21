package com.example.weathermvvm.data.repository

import com.example.weathermvvm.domain.repository.GetWeatherSearch
import com.example.weathermvvm.data.network.ApiService
import javax.inject.Inject

class GetWeatherSearchImpl @Inject constructor(
    private val apiService: ApiService
) : GetWeatherSearch {
    override suspend fun searchWeather(query: String) = apiService.getWeatherSearch(query)
}