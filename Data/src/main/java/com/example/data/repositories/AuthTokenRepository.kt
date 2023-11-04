package com.example.data.repositories

import android.telephony.TelephonyCallback.CarrierNetworkListener
import com.example.data.connections.NetworkListener
import com.example.data.offlineData.cache.CacheAuthToken
import com.example.data.remoteData.token.IRemoteAuthTokenDataSource
import com.example.domain.reposoitories.IAuthTokenRepository

class AuthTokenRepository(
    private val networkListener: NetworkListener,
    private val tokenDataSource: IRemoteAuthTokenDataSource,
    private val cachedToken: CacheAuthToken
) : IAuthTokenRepository {
    override suspend fun getToken(): String? {
        if (cachedToken.getToken() == null) {
            if (networkListener.getConnectivity()) {

                val response = tokenDataSource.getToken()
                if (response.isSuccessful) {
                    val authToken = response.body()
                    if (authToken != null) {
                        cachedToken.saveToken(authToken.access_token, authToken.expires_in)
                        return authToken.access_token
                    }
                }
            }
            return null

        } else {
            return cachedToken.getToken()
        }


    }


}