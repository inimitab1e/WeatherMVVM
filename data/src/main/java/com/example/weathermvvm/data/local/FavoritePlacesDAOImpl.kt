package com.example.weathermvvm.data.local

import android.content.Context
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import com.example.weathermvvm.domain.repository.local.FavoritePlacesDAO

class FavoritePlacesRepoImpl(context: Context) {

    var db: FavoritePlacesDAO = AppDatabase.getInstance(context)?.FavoritePlacesDAO()!!

    //Insert one place
    fun addPlaceToFavorite(favoritePlace: FavoritePlaces) {
        db.addPlaceToFavorite(favoritePlace)
    }

    //Select all
    fun getAllFavoritePlaces(): List<FavoritePlaces> = db.getAllFavoritePlaces()

    //Search if in the table
    fun  searchByName(name: String): FavoritePlaces = db.searchByName(name)

    //Delete one place
    fun removePlaceFromFavorite(name: String) {
        db.removePlaceFromFavorite(name)
    }
}