package com.example.weathermvvm.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchWeatherViewModel : ViewModel() {
    suspend fun onQueryChanged(query: String) {
        viewModelScope.launch {

        }
    }
}