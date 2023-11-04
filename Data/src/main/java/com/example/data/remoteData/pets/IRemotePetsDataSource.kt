package com.example.data.remoteData.pets

import com.example.data.responses.pets.PetsResponse
import retrofit2.Response

interface IRemotePetsDataSource {



    suspend fun getPets(page: Int, type: String, token: String): Response<PetsResponse?>
}