package com.example.weathermvvm.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

private val base_usrl = "http://api.weatherapi.com/v1/forecast.json?key=[api_key]q={Location}&days=7"

interface ApiService {


    @GET("q={Location}&days=7")
    suspend fun getWeatherSearch(@Path("Location") query: String) : Call<WeatherSearchResponse>
}