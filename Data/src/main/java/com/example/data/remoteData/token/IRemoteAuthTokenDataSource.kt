package com.example.data.remoteData.token

import com.example.data.responses.auth.AuthTokenResponse
import retrofit2.Response

interface IRemoteAuthTokenDataSource {



    suspend fun getToken(): Response<AuthTokenResponse>

}