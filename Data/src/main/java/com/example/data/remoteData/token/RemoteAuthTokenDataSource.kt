package com.example.data.remoteData.token


import com.example.data.apisEndPoints.ApiService
import com.example.data.responses.auth.AuthTokenResponse
import retrofit2.Response

class RemoteAuthTokenDataSource (private val apiService: ApiService): IRemoteAuthTokenDataSource {


    override suspend fun getToken(): Response<AuthTokenResponse?> {


        return apiService.getToken(
            "client_credentials",
            "8dwk3kbGjEoPvdDb08zReTo2oPIWPRJS5tUuayMaJ57qLWlBEO",
            "fgCvRsYqsVXDt8uThQW1IUhL9YSWPrecGarVyTzG"
        )

    }


}