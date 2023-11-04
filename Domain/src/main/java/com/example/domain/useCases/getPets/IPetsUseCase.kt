package com.example.domain.useCases.getPets

import com.example.domain.apiStates.PetsApiStates

interface IPetsUseCase {


   suspend fun getPets(type:String): PetsApiStates
   suspend fun loadMorePets(pagesLimit: Int, type: String): PetsApiStates
}