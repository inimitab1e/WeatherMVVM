package com.example.weathermvvm.domain.repository.local

import com.example.weathermvvm.domain.model.favorite.FavoritePlaces

interface FavoritePlacesLocalRepo {

    fun isInFavorite(name: String) : Boolean

    fun addToFavorites(favoritePlace: FavoritePlaces)

    fun removeFromFavorites(name: String)
}