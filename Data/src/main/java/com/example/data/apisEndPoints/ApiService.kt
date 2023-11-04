package com.example.data.apisEndPoints

import com.example.data.responses.auth.AuthTokenResponse
import com.example.data.responses.pets.PetsResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("/v2/oauth2/token")
   suspend fun getToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ):Response<AuthTokenResponse?>


    @GET("/v2/animals")
   suspend fun getPets(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("type") type: String
    ): Response<PetsResponse?>
}