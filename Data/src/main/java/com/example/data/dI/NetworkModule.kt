package com.example.data.dI

import android.content.Context
import com.example.data.offlineData.dataBase.OfflineDataBase
import com.example.data.utils.Constants.BaseUrl.BASE_URL
import com.example.data.remoteData.pets.IRemotePetsDataSource
import com.example.data.remoteData.pets.RemotePetsDataSource
import com.example.data.remoteData.token.IRemoteAuthTokenDataSource
import com.example.data.remoteData.token.RemoteAuthTokenDataSource
import com.example.data.apisEndPoints.ApiService
import com.example.data.offlineData.cache.CacheAuthToken
import com.example.data.repositories.AuthTokenRepository
import com.example.data.repositories.PetsRepository
import com.example.domain.reposoitories.IAuthTokenRepository
import com.example.domain.reposoitories.IPetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun getPetRepo(remotePetsDataStore: IRemotePetsDataSource,offlineDataBase: OfflineDataBase): IPetsRepository {

        return PetsRepository(remotePetsDataStore,offlineDataBase)
    }

    @Provides
    fun getAuthRepo(tokenDataSource: IRemoteAuthTokenDataSource, cachedTokenDataSource: CacheAuthToken): IAuthTokenRepository {

        return AuthTokenRepository(tokenDataSource,cachedTokenDataSource)
    }
    @Provides
    fun getRemotePetsDataStore(apiService: ApiService): IRemotePetsDataSource {

        return RemotePetsDataSource(apiService)
    }

    @Provides
    fun getTokenDataSource(apiService: ApiService): IRemoteAuthTokenDataSource {

        return RemoteAuthTokenDataSource(apiService)
    }
    @Provides
    fun getCachedTokenDataSource(@ApplicationContext context : Context): CacheAuthToken {

        return CacheAuthToken(context)
    }


    @Provides
    fun getApiService(): ApiService {
        val retrofitInstance = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitInstance.create(ApiService::class.java)


    }
}

