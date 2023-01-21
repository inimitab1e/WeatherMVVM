package com.example.weathermvvm.domain.model

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Condition(
    @SerializedName("code")
    val code: Int,
    @SerializedName("text")
    val text: String
)