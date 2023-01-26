package com.example.weathermvvm.domain.model.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavoritePlaces(
    @PrimaryKey(autoGenerate = true) var ID: Int? = null,
    var placeName: String?,
    var latitude: Double?,
    var longitude: Double?
)