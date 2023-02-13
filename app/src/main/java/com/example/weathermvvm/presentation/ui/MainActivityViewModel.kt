package com.example.weathermvvm.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var _favoriteLocationNameToShare = MutableLiveData<String>()
    val favoriteLocationNameToShare: LiveData<String> get() = _favoriteLocationNameToShare

    fun setFavoriteLocationNameToShare(locationName: String) {
        _favoriteLocationNameToShare.postValue(locationName)
    }
}