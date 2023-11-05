package com.example.domain.useCases.getPets

import com.example.domain.apiStates.PetsApiStates
import com.example.domain.reposoitories.IAuthTokenRepository
import com.example.domain.reposoitories.IPetsRepository

class PetsUseCase(
    private val petsRepository: IPetsRepository, private val authTokenRepo: IAuthTokenRepository
) : IPetsUseCase {

    override suspend fun getPets(type: String): PetsApiStates {

        val token = authTokenRepo.getToken()

        return petsRepository.getPets(1, type, token)


    }

    override suspend fun loadMorePets(currentPage:Int,pagesLimit:Int,type: String): PetsApiStates? {
        val token = authTokenRepo.getToken()

        if (currentPage+1<=pagesLimit) {
            return petsRepository.getPets(currentPage+1, type, token)
        }

        return null

    }


}