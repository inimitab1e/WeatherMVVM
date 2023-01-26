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

    @Query("Select * from favourites where placeName like :name")
    fun searchByName(name: String): FavoritePlaces

    @Query("DELETE FROM favourites where placeName like :name")
    fun removePlaceFromFavorite(name: String)
}