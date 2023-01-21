package com.example.weathermvvm.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermvvm.domain.model.WeatherResponse
import com.example.weathermvvm.domain.repository.GetWeatherSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchWeatherViewModel @Inject constructor(
    private val getWeatherSearchRepository: GetWeatherSearch
) : ViewModel() {

    private var _weatherOnSuccessResponse = MutableLiveData<WeatherResponse>()
    val weatherOnSuccessResponse get() = _weatherOnSuccessResponse

    fun getResponse(query: String) {
        viewModelScope.launch {
            weatherOnSuccessResponse.value = onQueryChanged(query)
        }
    }

    private suspend fun onQueryChanged(query: String): WeatherResponse? {
        val a = getWeatherSearchRepository.searchWeather(query).body()
        return a
    }
}