package com.example.weathermvvm.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var _favoriteLocationNameToShare = MutableLiveData<String>()
    val favoriteLocationNameToShare get() = _favoriteLocationNameToShare

    fun setFavoriteLocationNameToShare(locationName: String) {
        favoriteLocationNameToShare.postValue(locationName)
    }
}