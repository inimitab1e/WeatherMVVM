package com.example.weathermvvm.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SettingsSharedPreferences(context: Context) {
    private var sharedPref: SharedPreferences

    init {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getAppThemeState(): Boolean =
        sharedPref.getBoolean(StringConstants.preferenceThemeKey, false)
}