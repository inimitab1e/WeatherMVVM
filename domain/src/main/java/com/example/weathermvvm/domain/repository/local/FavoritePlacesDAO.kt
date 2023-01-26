package com.example.weathermvvm.domain.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces

@Dao
interface FavoritePlacesDAO {

    @Insert
    fun addPlaceToFavorite(favoritePlace: FavoritePlaces)

    @Query("Select * from favourites")
    fun getAllFavoritePlaces(): List<FavoritePlaces>

    @Delete
    fun removePlaceFromFavorite(favoritePlace: FavoritePlaces)
}