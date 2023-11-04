package com.example.data.dataMapper

import com.example.data.entities.PetsEntity
import com.example.data.responses.pets.PetsResponse
import com.example.domain.models.Pagination
import com.example.domain.models.Pets
import com.example.domain.models.PetsModel

object PetsDataMapper {

    fun fromApiResponseToPetsDataBaseEntity(apiResponse: PetsResponse): List<PetsEntity> {
        val pets = apiResponse.animals
        val petsList = mutableListOf<PetsEntity>()
        for (pet in pets) {
            val entity = PetsEntity(
                pet.id,
                pet.name,
                pet.gender,
                pet.size,
                pet.type,
                pet.colors.primary,
                pet.photos[0].small,
                pet.photos[0].medium,
                pet.contact.address.city,
                pet.contact.address.state,
                pet.contact.address.country
            )
            petsList.add(entity)
        }
        return petsList
    }

    fun fromApiResponseToPetsModel(apiResponse: PetsResponse): PetsModel {
        val pagination = Pagination(
            apiResponse.pagination.count_per_page,
            apiResponse.pagination.current_page,
            apiResponse.pagination.total_count,
            apiResponse.pagination.total_pages
        )

        val pets = apiResponse.animals
        val petsList = mutableListOf<Pets>()
        for (pet in pets) {
            val model = Pets(
                pet.id,
                pet.name,
                pet.gender,
                pet.size,
                pet.type,
                pet.colors.primary,
                pet.photos[0].small,
                pet.photos[0].medium,
                pet.contact.address.city,
                pet.contact.address.state,
                pet.contact.address.country

            )
            petsList.add(model)
        }

        return PetsModel(petsList, pagination)
    }

    fun fromPetsDataBaseEntityToPetsModel(petsEntity: List<PetsEntity>): PetsModel {
        val pagination=Pagination(0,0,0,0)
        val petsList = mutableListOf<Pets>()
        for (pet in petsEntity) {
            val model = Pets(
                pet.id,
                pet.name,
                pet.gender,
                pet.size,
                pet.type,
                pet.primaryColor,
                pet.smallPhoto,
                pet.mediumPhoto,
                pet.city,
                pet.state,
                pet.country
            )
            petsList.add(model)
        }
        return PetsModel(petsList, pagination)
    }

}