package com.example.weathermvvm.data.local

import android.content.Context
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import com.example.weathermvvm.domain.repository.local.FavoritePlacesDAO

class FavoritePlacesDAOImpl(context: Context) {

    var db: FavoritePlacesDAO = AppDatabase.getInstance(context)?.FavoritePlacesDAO()!!

    //Insert one place
    suspend fun addPlaceToFavorite(favoritePlace: FavoritePlaces) {
        db.addPlaceToFavorite(favoritePlace)
    }

    //Select all
    suspend fun getAllFavoritePlaces(): MutableList<FavoritePlaces> = db.getAllFavoritePlaces()

    //Search if in the table
    suspend fun searchByName(name: String): Boolean = db.searchByName(name)

    //Delete one place
    suspend fun removePlaceFromFavoriteByName(name: String) {
        db.removePlaceFromFavoriteByName(name)
    }
}