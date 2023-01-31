package com.example.weathermvvm.domain.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("message")
    val message: String
)