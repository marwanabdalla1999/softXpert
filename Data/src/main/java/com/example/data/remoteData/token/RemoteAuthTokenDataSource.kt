package com.example.data.remoteData.token


import com.example.data.apisEndPoints.ApiService
import com.example.data.responses.auth.AuthTokenResponse
import com.example.data.utils.Constants
import retrofit2.Response

class RemoteAuthTokenDataSource (private val apiService: ApiService): IRemoteAuthTokenDataSource {


    override suspend fun getToken(): Response<AuthTokenResponse?> {


        return apiService.getToken(
            Constants.Credentials.GRANT_TYPE,
            Constants.Credentials.CLIENT_ID,
            Constants.Credentials.CLIENT_SECRET
        )

    }


}