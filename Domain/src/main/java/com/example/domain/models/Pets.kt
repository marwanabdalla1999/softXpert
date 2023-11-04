package com.example.domain.models

import java.io.Serializable

data class Pets(

    val id: Int,
    val name: String = "NA",
    val gender: String = "NA",
    val size: String = "NA",
    val type: String = "NA",
    val primaryColor: String? = "NA",
    val smallPhoto: String?,
    val mediumPhoto: String?,
    val address: String = "NA"
) : Serializable