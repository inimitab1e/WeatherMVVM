package com.example.weathermvvm.di

import android.content.Context
import com.example.weathermvvm.data.AppDispatchers
import com.example.weathermvvm.data.local.FavoritePlacesDAOImpl
import com.example.weathermvvm.utils.SettingsSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalRepositoryModule {

    @Provides
    @Singleton
    fun provideFavoritePlacesLocalRepo(
        @ApplicationContext context: Context,
        appDispatchers: AppDispatchers
    ): FavoritePlacesDAOImpl =
        FavoritePlacesDAOImpl(context = context, dispatchers = appDispatchers)
}