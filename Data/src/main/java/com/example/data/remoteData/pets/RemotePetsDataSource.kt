package com.example.data.remoteData.pets

import com.example.data.apisEndPoints.ApiService
import com.example.data.responses.pets.PetsResponse
import retrofit2.Response

class RemotePetsDataSource (private val apiService: ApiService): IRemotePetsDataSource {


    override suspend fun getPets(page: Int, type: String, token: String): Response<PetsResponse?> {

        return apiService.getPets("Bearer $token", page, type)

    }


}