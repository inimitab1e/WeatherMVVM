package com.example.weathermvvm.di

import android.content.Context
import com.example.weathermvvm.data.local.FavoritePlacesRepoImpl
import com.example.weathermvvm.domain.repository.GetWeatherSearch
import com.example.weathermvvm.data.network.ApiService
import com.example.weathermvvm.data.repository.FavoritePlacesLocalImpl
import com.example.weathermvvm.data.repository.GetWeatherSearchImpl
import com.example.weathermvvm.domain.repository.local.FavoritePlacesLocalRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGetWeatherSearch(client: Retrofit) : GetWeatherSearch =
        GetWeatherSearchImpl(apiService = client.create(ApiService::class.java))

    @Provides
    @Singleton
    fun provideCheckIfFavoriteRepository(@ApplicationContext context: Context) : FavoritePlacesLocalRepo =
        FavoritePlacesLocalImpl(localRepo = FavoritePlacesRepoImpl(context = context))
}