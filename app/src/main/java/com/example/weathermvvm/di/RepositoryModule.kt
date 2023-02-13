package com.example.weathermvvm.di

import com.example.weathermvvm.data.AppDispatchers
import com.example.weathermvvm.domain.repository.GetWeatherSearch
import com.example.weathermvvm.data.network.ApiService
import com.example.weathermvvm.data.repository.GetWeatherSearchImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGetWeatherSearch(apiService: ApiService, appDispatchers: AppDispatchers) : GetWeatherSearch =
        GetWeatherSearchImpl(apiService = apiService, dispatchers = appDispatchers)
}