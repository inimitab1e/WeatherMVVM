package com.example.weathermvvm.domain.network_features.result

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val url: String?
}
