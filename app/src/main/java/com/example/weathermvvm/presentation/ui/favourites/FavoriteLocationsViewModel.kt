package com.example.weathermvvm.presentation.ui.favourites

import androidx.lifecycle.*
import com.example.weathermvvm.data.local.FavoritePlacesDAOImpl
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteLocationsViewModel @Inject constructor(
    private val localRepository: FavoritePlacesDAOImpl
) : ViewModel() {

    val listOfFavorites: LiveData<List<FavoritePlaces>?> = liveData {
        emit(localRepository.getAllFavoritePlaces())
    }

    fun deletePlaceFromListOfFavorites(name: String) {
        viewModelScope.launch {
            localRepository.removePlaceFromFavoriteByName(name = name)
        }
    }

    fun addToFavorite(name: String, latitude: Double, longitude: Double) {
        viewModelScope.launch {
            localRepository.addPlaceToFavorite(
                FavoritePlaces(
                    placeName = name,
                    latitude = latitude,
                    longitude = longitude
                )
            )
        }
    }
}