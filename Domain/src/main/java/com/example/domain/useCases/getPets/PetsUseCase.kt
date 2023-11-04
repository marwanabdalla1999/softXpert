package com.example.domain.useCases.getPets

import com.example.domain.apiStates.PetsApiStates
import com.example.domain.reposoitories.IAuthTokenRepository
import com.example.domain.reposoitories.IPetsRepository

class PetsUseCase(
    private val petsRepository: IPetsRepository, private val authTokenRepo: IAuthTokenRepository
) : IPetsUseCase {

    private var page=1
    override suspend fun getPets(type: String): PetsApiStates {

        val token = authTokenRepo.getToken()
        page=1
        return petsRepository.getPets(page, type, token)


    }

    override suspend fun loadMorePets(pagesLimit:Int,type: String): PetsApiStates {

        if (page+1<=pagesLimit) {
            page++
        }

        val token = authTokenRepo.getToken()
        return petsRepository.getPets(page, type, token)

    }


}