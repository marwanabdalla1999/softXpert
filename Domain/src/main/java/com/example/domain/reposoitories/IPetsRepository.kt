package com.example.domain.reposoitories

import com.example.domain.apiStates.PetsApiStates

interface IPetsRepository {



    suspend fun getPets(page:Int,type: String, token: String?): PetsApiStates
}