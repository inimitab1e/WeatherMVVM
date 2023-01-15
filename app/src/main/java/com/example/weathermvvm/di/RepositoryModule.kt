package com.example.weathermvvm.di

import com.example.weathermvvm.data.repository.GetWeatherSearchImpl
import com.example.weathermvvm.domain.repository.GetWeatherSearch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGetWeatherSearch() : GetWeatherSearch {
        return GetWeatherSearchImpl()
    }
}