package com.example.data.offlineData.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.PetsEntity

@Database(entities = [PetsEntity::class], version = 1)
abstract class OfflineDataBase : RoomDatabase() {
    abstract fun Dao(): Dao
}