package com.example.get_bearer_token_app

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api-clients/")
    suspend fun getToken(@Body tokenRequest: TokenRequest): TokenResponse
}