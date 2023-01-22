package com.example.weathermvvm.data.network

import com.example.weathermvvm.domain.model.coordinates.LocationCoordsResponse
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("direct?")
    suspend fun getCoordinates(
        @Url url: String,
        @Query("q") locationName: String,
        @Query("appid") apiKey: String
    ): Call<LocationCoordsResponse>

    @GET("forecast?")
    suspend fun getWeatherSearch(
        @Url url: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): Call<WeatherSearchResponse>
}