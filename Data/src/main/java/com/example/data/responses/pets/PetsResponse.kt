package com.example.data.responses.pets

data class PetsResponse(
    val animals: List<Animal>,
    val pagination: Pagination
)