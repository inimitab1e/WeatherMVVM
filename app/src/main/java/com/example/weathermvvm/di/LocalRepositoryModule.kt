package com.example.weathermvvm.di

import android.content.Context
import com.example.weathermvvm.data.local.FavoritePlacesDAOImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalRepositoryModule {

    @Provides
    @Singleton
    fun provideFavoritePlacesLocalRepo(
        @ApplicationContext context: Context,
        coroutineDispatcher: CoroutineDispatcher
    ): FavoritePlacesDAOImpl =
        FavoritePlacesDAOImpl(context = context, ioDispatcher = coroutineDispatcher)
}