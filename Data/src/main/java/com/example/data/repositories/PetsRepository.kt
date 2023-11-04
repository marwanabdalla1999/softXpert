package com.example.data.repositories

import com.example.data.connections.NetworkListener
import com.example.data.offlineData.dataBase.OfflineDataBase
import com.example.data.dataMapper.PetsDataMapper
import com.example.data.remoteData.pets.IRemotePetsDataSource
import com.example.data.responses.pets.PetsResponse
import com.example.data.utils.Constants
import com.example.domain.apiStates.PetsApiStates
import com.example.domain.reposoitories.IPetsRepository
import retrofit2.Response

class PetsRepository(
    private val networkListener: NetworkListener,
    private val remotePetsData: IRemotePetsDataSource,
    private val offlineDataBase: OfflineDataBase
) : IPetsRepository {


    override suspend fun getPets(page: Int, type: String, token: String?): PetsApiStates {

        return execute(page, type, token)
    }

    private suspend fun execute(page: Int, type: String, token: String?): PetsApiStates {
        if (token == null) {
            return getOfflineData(type)
        } else {
            if (networkListener.getConnectivity()) {
                val response = remotePetsData.getPets(page, type, token)

                if (response.isSuccessful) {
                    return getRemotedData(response)
                }

            }
            return getOfflineData(type)


        }
    }

    private suspend fun getRemotedData(response: Response<PetsResponse?>): PetsApiStates {
        val data = response.body()

        val petsEntity = data?.let { PetsDataMapper.fromApiResponseToPetsDataBaseEntity(it) }
        val petsModel = data?.let { PetsDataMapper.fromApiResponseToPetsModel(it) }

        if (petsEntity != null) {
            offlineDataBase.Dao().insertPets(petsEntity)
        }

        return PetsApiStates.Success(petsModel)

    }

    private suspend fun getOfflineData(type:String): PetsApiStates {
        val data = if (type==""){
            offlineDataBase.Dao().getAllPets()
        } else{
            offlineDataBase.Dao().getAllPetsOfOneType(type)
        }
        val petsModel = data.let { PetsDataMapper.fromPetsDataBaseEntityToPetsModel(it) }
        return PetsApiStates.Failure(
            Throwable(Constants.Errors.OFFLINE_MODE), petsModel
        )
    }
}