package com.example.weathermvvm.data.network

import com.example.weathermvvm.domain.model.ErrorResponse
import com.example.weathermvvm.domain.model.coordinates.LocationCoordsResponse
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse
import com.example.weathermvvm.domain.network_features.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getCoordinates(
        @Url url: String,
        @Query("q") locationName: String,
        @Query("appid") apiKey: String,
        @Query("units") mode: String = "metric"
    ): NetworkResponse<LocationCoordsResponse, ErrorResponse>

    @GET
    suspend fun getWeatherSearch(
        @Url url: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") mode: String = "metric"
    ): NetworkResponse<WeatherSearchResponse, ErrorResponse>
}