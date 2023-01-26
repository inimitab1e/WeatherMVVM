package com.example.weathermvvm.domain.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces

@Dao
interface FavoritePlacesDAO {

    @Insert
    suspend fun addPlaceToFavorite(favoritePlace: FavoritePlaces)

    @Query("Select * from favourites")
    suspend fun getAllFavoritePlaces(): List<FavoritePlaces>

    @Query("SELECT EXISTS(SELECT * FROM favourites WHERE placeName = :name)")
    suspend fun searchByName(name: String): Boolean

    @Query("DELETE FROM favourites where placeName like :name")
    suspend fun removePlaceFromFavorite(name: String)
}