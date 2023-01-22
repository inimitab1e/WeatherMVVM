package com.example.weathermvvm.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse
import com.example.weathermvvm.domain.repository.GetWeatherSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchWeatherViewModel @Inject constructor(
    private val getWeatherSearchRepository: GetWeatherSearch,
) : ViewModel() {
    private var _weatherOnSuccessResponse = MutableLiveData<WeatherSearchResponse?>()
    val weatherOnSuccessResponse get() = _weatherOnSuccessResponse

    fun getResponse(query: String) {
        viewModelScope.launch {
            weatherOnSuccessResponse.value = onQueryChanged(query)
        }
    }

    private suspend fun onQueryChanged(query: String): WeatherSearchResponse? {
        val coordsResponse = getWeatherSearchRepository.getCoordinatesByName(locationName = query)
        return if (coordsResponse.isSuccessful) {
            val latitude = coordsResponse.body()!![0].lat
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