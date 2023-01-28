package com.example.weathermvvm.presentation.ui.favourites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermvvm.data.local.FavoritePlacesDAOImpl
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteLocationsViewModel @Inject constructor(
    private val localRepository: FavoritePlacesDAOImpl
) : ViewModel() {

    private var _listOfFavorites = MutableLiveData<List<FavoritePlaces>>()
    val listOfFavorites get() = _listOfFavorites

    private var _selectedLocation = MutableLiveData<String>()
    val selectedLocation get() = _selectedLocation

    fun getListOfFavoritePlaces() {
        viewModelScope.launch {
            listOfFavorites.postValue(localRepository.getAllFavoritePlaces())
        }
    }

    fun useSelectedLocation(name: String) {
        selectedLocation.postValue(name)
    }
}