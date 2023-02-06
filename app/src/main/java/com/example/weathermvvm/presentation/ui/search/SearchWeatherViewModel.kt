package com.example.weathermvvm.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermvvm.data.local.FavoritePlacesDAOImpl
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse
import com.example.weathermvvm.domain.network_features.NetworkResponse
import com.example.weathermvvm.domain.repository.GetWeatherSearch
import com.example.weathermvvm.utils.StringConstants
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

    private var _weatherOnSuccessEmptyResponse = MutableLiveData<Boolean>()
    val weatherOnSuccessEmptyResponse get() = _weatherOnSuccessEmptyResponse

    private var _isLocationInFavorite = MutableLiveData<Boolean>()
    val isLocationInFavorite get() = _isLocationInFavorite

    private var _onErrorResponse = MutableLiveData<String>()
    val onErrorResponse get() = _onErrorResponse

    private var _isLoading = MutableLiveData(false)
    val isLoading get() = _isLoading

    fun getResponse(query: String) {
        isLoading.postValue(true)
        viewModelScope.launch {
            when(val coordsResponse = getWeatherSearchRepository.getCoordinatesByName(query)) {
                is NetworkResponse.Success -> if (coordsResponse.body.isNotEmpty()) {
                    weatherOnSuccessEmptyResponse.postValue(false)
                    getWeatherResponse(
                        latitude = coordsResponse.body[0].lat,
                        longitude = coordsResponse.body[0].lon
                    )
                } else {
                    weatherOnSuccessEmptyResponse.postValue(true)
                    isLoading.postValue(false)
                }
                is NetworkResponse.NetworkError -> onErrorResponse.postValue(coordsResponse.error.message)
                else -> onErrorResponse.postValue(StringConstants.unknownError)
            }
            isLoading.postValue(false)
        }
    }

    private suspend fun getWeatherResponse(latitude: Double, longitude: Double) {
        when(val response = getWeatherSearchRepository.getWeatherByCoordinates(latitude, longitude)) {
            is NetworkResponse.Success -> weatherOnSuccessResponse.postValue(response.body)
            is NetworkResponse.NetworkError -> onErrorResponse.postValue(response.error.message)
            else -> onErrorResponse.postValue(StringConstants.unknownError)
        }
        isLoading.postValue(false)
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