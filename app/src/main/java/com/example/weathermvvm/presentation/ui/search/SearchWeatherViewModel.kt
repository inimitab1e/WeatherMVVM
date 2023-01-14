package com.example.weathermvvm.presentation.ui.search

import androidx.lifecycle.MutableLiveData

interface SearchWeatherViewModel {
    val items: MutableLiveData<List<>>

    fun onQueryChanged(query: String)
}