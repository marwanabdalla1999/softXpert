package com.example.data.dI

import android.content.Context
import androidx.room.Room
import com.example.data.offlineData.dataBase.OfflineDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object OfflineDatabaseModule {


    @Provides
    fun getOfflineDatabase(@ApplicationContext context:Context): OfflineDataBase {

        return Room.databaseBuilder(context, OfflineDataBase::class.java, "PetsDataBase")
            .build()
    }
}