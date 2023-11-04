package com.example.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Pets")
data class PetsEntity (

    @PrimaryKey val id: Int,
    val name: String,
    val gender: String,
    val size: String,
    val type:String,
    val primaryColor: String?,
    val smallPhoto: String?,
    val mediumPhoto: String?,
    val city: String,
    val state: String,
    val country: String,


    )