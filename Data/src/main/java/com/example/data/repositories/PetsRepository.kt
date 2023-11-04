package com.example.data.repositories

import com.example.data.offlineData.dataBase.OfflineDataBase
import com.example.data.dataMapper.PetsDataMapper
import com.example.data.remoteData.pets.IRemotePetsDataSource
import com.example.data.utils.Constants
import com.example.domain.apiStates.PetsApiStates
import com.example.domain.reposoitories.IPetsRepository

class PetsRepository(
    private val remotePetsData: IRemotePetsDataSource, private val offlineDataBase: OfflineDataBase
) : IPetsRepository {


    override suspend fun getPets(page: Int, type: String, token: String?): PetsApiStates {
        if (token == null) {
            val data = offlineDataBase.Dao().getAllPets()
            val petsModel = data.let { PetsDataMapper.fromPetsDataBaseEntityToPetsModel(it) }
            return PetsApiStates.Failure(
                Throwable(Constants.Errors.UNKNOWN_ERROR), petsModel
            )

        } else {

            val response = remotePetsData.getPets(page, type, token)
            val retData: PetsApiStates = if (response.isSuccessful) {
                val data = response.body()

                val petsEntity =
                    data?.let { PetsDataMapper.fromApiResponseToPetsDataBaseEntity(it) }
                val petsModel = data?.let { PetsDataMapper.fromApiResponseToPetsModel(it) }

                if (petsEntity != null) {
                    offlineDataBase.Dao().insertPets(petsEntity)
                }

                PetsApiStates.Success(petsModel)
            } else {
                val data = offlineDataBase.Dao().getAllPets()
                val petsModel = data.let { PetsDataMapper.fromPetsDataBaseEntityToPetsModel(it) }

                PetsApiStates.Failure(
                    Throwable(Constants.Errors.UNKNOWN_ERROR), petsModel
                )
            }
            return retData
        }
    }
}