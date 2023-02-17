package com.example.weathermvvm.data.local

import android.content.Context
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import com.example.weathermvvm.domain.repository.local.FavoritePlacesDAO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoritePlacesDAOImpl @Inject constructor(
    context: Context,
    private val ioDispatcher: CoroutineDispatcher
) {

    var db: FavoritePlacesDAO = AppDatabase.getInstance(context)?.FavoritePlacesDAO()!!

    //Insert one place
    suspend fun addPlaceToFavorite(favoritePlace: FavoritePlaces) {
        withContext(ioDispatcher) {
            db.addPlaceToFavorite(favoritePlace)
        }
    }

    //Select all
    suspend fun getAllFavoritePlaces(): MutableList<FavoritePlaces> =
        withContext(ioDispatcher) { db.getAllFavoritePlaces() }

    //Search if in the table
    suspend fun searchByName(name: String): Boolean =
        withContext(ioDispatcher) { db.searchByName(name) }

    //Delete one place
    suspend fun removePlaceFromFavoriteByName(name: String) {
        withContext(ioDispatcher) {
            db.removePlaceFromFavoriteByName(name)
        }
    }
}