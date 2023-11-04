package com.example.data.utils

class Constants {

    object BaseUrl {
        private const val PRIVATE_BASE_URL = "https://api.petfinder.com"
        const val BASE_URL = PRIVATE_BASE_URL
    }
    object Errors{
        const val OFFLINE_MODE="You are offline try again to update pets"

        const val UNKNOWN_ERROR="there is a problem in your internet connection please try again"

    }



}