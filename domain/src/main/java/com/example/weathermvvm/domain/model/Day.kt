package com.example.weathermvvm.domain.model

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Day(
    @SerializedName("avghumidity")
    val avghumidity: Int,
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("maxtemp_c")
    val maxtemp_c: Double,
    @SerializedName("maxwind_mph")
    val maxwind_mph: Double,
    @SerializedName("mintemp_c")
    val mintemp_c: Double
)