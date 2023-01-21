package com.example.weathermvvm.data.network

import com.example.weathermvvm.domain.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("forecast.json?key={api_key}q={Location}&days=7")
    suspend fun getWeatherSearch(@Path("Location") query: String) : Response<WeatherResponse>
}