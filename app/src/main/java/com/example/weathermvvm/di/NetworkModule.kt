package com.example.weathermvvm.di

import com.example.weathermvvm.BuildConfig
import com.example.weathermvvm.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val base_url_coordinates = BuildConfig.base_url_coordinates

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(mOkHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_url_coordinates)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: Retrofit) : ApiService {
        return client.create(ApiService::class.java)
    }
}