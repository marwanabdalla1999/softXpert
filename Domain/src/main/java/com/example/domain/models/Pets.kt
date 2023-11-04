package com.example.domain.models

data class Pets (

    val id: Int,
    val name: String,
    val gender: String,
    val size: String,
    val type:String,
    val primaryColor: String?,
    val smallPhoto: String?,
    val mediumPhoto: String?,
    val city: String,
    val state: String,
    val country: String
)