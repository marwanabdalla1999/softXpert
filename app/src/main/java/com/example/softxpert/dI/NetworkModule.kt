package com.example.softxpert.dI

import com.example.domain.reposoitories.IAuthTokenRepository
import com.example.domain.reposoitories.IPetsRepository
import com.example.domain.useCases.getPets.IPetsUseCase
import com.example.domain.useCases.getPets.PetsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
  fun getPetsUseCase(petsRepository: IPetsRepository,authTokenRepository: IAuthTokenRepository): IPetsUseCase {

      return PetsUseCase(petsRepository,authTokenRepository)
  }


}