package com.example.weathermvvm.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("q={Location}&days=7")
    suspend fun getWeatherSearch(@Path("Location") query: String) : Call<WeatherSearchResponse>
}