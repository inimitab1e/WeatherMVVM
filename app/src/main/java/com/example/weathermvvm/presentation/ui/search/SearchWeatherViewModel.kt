package com.example.weathermvvm.presentation.ui.search


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermvvm.data.local.FavoritePlacesDAOImpl
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse
import com.example.weathermvvm.domain.repository.GetWeatherSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchWeatherViewModel @Inject constructor(
    private val getWeatherSearchRepository: GetWeatherSearch,
    private val localRepository: FavoritePlacesDAOImpl
) : ViewModel() {

    private var _weatherOnSuccessResponse = MutableLiveData<WeatherSearchResponse?>()
    val weatherOnSuccessResponse get() = _weatherOnSuccessResponse

    private var _isLocationInFavorite = MutableLiveData<Boolean>()
    val isLocationInFavorite get() = _isLocationInFavorite

    fun getResponse(query: String) {
        viewModelScope.launch {
            weatherOnSuccessResponse.postValue(getWeatherSearchRepository.searchWeather(query))
        }
    }

    fun checkIfLocationInFavorite(name: String) {
        viewModelScope.launch {
            isLocationInFavorite.postValue(localRepository.searchByName(name = name))
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

    fun deletePlaceFromListOfFavorites(name: String) {
        viewModelScope.launch {
            localRepository.removePlaceFromFavoriteByName(name = name)
        }
    }
}