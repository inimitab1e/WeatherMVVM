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

    private var _listOfFavorites = MutableLiveData<List<FavoritePlaces>?>()
    val listOfFavorites get() = _listOfFavorites

    private var _isLocationInFavorite = MutableLiveData<Boolean>()
    val isLocationInFavorite get() = _isLocationInFavorite

    fun getResponse(query: String) {
        viewModelScope.launch {
            weatherOnSuccessResponse.postValue(onQueryChanged(query))
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

    fun getListOfFavoritePlaces() {
        viewModelScope.launch {
            listOfFavorites.postValue(localRepository.getAllFavoritePlaces())
        }
    }

    fun deletePlaceFromListOfFavorites(name: String) {
        viewModelScope.launch {
            localRepository.removePlaceFromFavorite(name = name)
        }
    }

    private suspend fun onQueryChanged(query: String): WeatherSearchResponse? {
        val coordsResponse = getWeatherSearchRepository.getCoordinatesByName(locationName = query)
        return if (coordsResponse.body()?.isEmpty() == false) {
            val latitude: Double = coordsResponse.body()!![0].lat
            val longitude = coordsResponse.body()!![0].lon

            getWeatherSearchRepository.searchWeather(
                latitude = latitude,
                longitude = longitude
            ).body()
        } else {
            null
        }
    }
}