package com.example.weathermvvm.presentation.ui.search

import androidx.lifecycle.LiveData
import com.example.weathermvvm.domain.network_features.result.Result
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermvvm.data.local.FavoritePlacesDAOImpl
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse
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
    val weatherOnSuccessResponse: LiveData<WeatherSearchResponse?> get() = _weatherOnSuccessResponse

    private var _weatherOnSuccessEmptyResponse = MutableLiveData<Boolean>()
    val weatherOnSuccessEmptyResponse: LiveData<Boolean> get() = _weatherOnSuccessEmptyResponse

    private var _isLocationInFavorite = MutableLiveData<Boolean>()
    val isLocationInFavorite: LiveData<Boolean> get() = _isLocationInFavorite

    private var _onErrorResponse = MutableLiveData<String>()
    val onErrorResponse: LiveData<String> get() = _onErrorResponse

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getResponse(query: String) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            when(val coordsResponse = getWeatherSearchRepository.getCoordinatesByName(query)) {
                is Result.Success -> if (coordsResponse.value.isNotEmpty()) {
                    _weatherOnSuccessEmptyResponse.postValue(false)
                    getWeatherResponse(
                        latitude = coordsResponse.value[0].lat,
                        longitude = coordsResponse.value[0].lon
                    )
                } else {
                    _weatherOnSuccessEmptyResponse.postValue(true)
                    _isLoading.postValue(false)
                }
                is Result.Failure<*> -> _onErrorResponse.postValue(coordsResponse.toString())
                else -> _onErrorResponse.postValue(StringConstants.unknownError)
            }
            _isLoading.postValue(false)
        }
    }

    private suspend fun getWeatherResponse(latitude: Double, longitude: Double) {
        when(val response = getWeatherSearchRepository.getWeatherByCoordinates(latitude, longitude)) {
            is Result.Success -> _weatherOnSuccessResponse.postValue(response.value)
            is Result.Failure<*> -> _onErrorResponse.postValue(response.toString())
            else -> _onErrorResponse.postValue(StringConstants.unknownError)
        }
        _isLoading.postValue(false)
    }

    fun checkIfLocationInFavorite(name: String) {
        viewModelScope.launch {
            _isLocationInFavorite.postValue(localRepository.searchByName(name = name))
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

    fun clearLiveDataValue() {
        _weatherOnSuccessResponse.postValue(null)
    }
}