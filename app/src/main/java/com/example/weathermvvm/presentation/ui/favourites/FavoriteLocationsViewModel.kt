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

    fun getListOfFavoritePlaces() {
        viewModelScope.launch {
            listOfFavorites.postValue(localRepository.getAllFavoritePlaces())
        }
    }
}