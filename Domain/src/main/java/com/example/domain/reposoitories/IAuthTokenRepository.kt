package com.example.domain.reposoitories

interface IAuthTokenRepository {


    suspend fun getToken(): String?
}