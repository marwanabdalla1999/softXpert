package com.example.data.offlineData.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entities.PetsEntity

@Dao
interface Dao {
    @Query("SELECT * FROM pets")
    suspend fun getAllPets(): List<PetsEntity>


    @Query("SELECT * FROM pets WHERE type")
    suspend fun getAllPetsOfOneType(types:String): List<PetsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPets(pets: List<PetsEntity>)




}