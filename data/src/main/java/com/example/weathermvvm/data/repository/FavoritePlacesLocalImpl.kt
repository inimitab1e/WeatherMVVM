package com.example.weathermvvm.data.repository

import com.example.weathermvvm.data.local.FavoritePlacesRepoImpl
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import com.example.weathermvvm.domain.repository.local.FavoritePlacesLocalRepo
import javax.inject.Inject

class FavoritePlacesLocalImpl @Inject constructor(
    private val localRepo: FavoritePlacesRepoImpl
) : FavoritePlacesLocalRepo {
    override fun isInFavorite(name: String): Boolean {
        return localRepo.searchByName(name = name).ID != null
    }

    override fun addToFavorites(favoritePlace: FavoritePlaces) {
        localRepo.addPlaceToFavorite(favoritePlace = favoritePlace)
    }

    override fun removeFromFavorites(name: String) {
        localRepo.removePlaceFromFavorite(name = name)
    }
}