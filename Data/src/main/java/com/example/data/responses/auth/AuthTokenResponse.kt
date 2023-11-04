package com.example.data.responses.auth

data class AuthTokenResponse(
    val access_token: String,
    val expires_in: Long,
    val token_type: String
)