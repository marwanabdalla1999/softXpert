package com.example.data.utils

class Constants {

    object BaseUrl {
        private const val PRIVATE_BASE_URL = "https://api.petfinder.com"
        const val BASE_URL = PRIVATE_BASE_URL
    }

    object Errors {
        const val OFFLINE_MODE = "You are offline try again to update pets"

        const val UNKNOWN_ERROR = "there is a problem in your internet connection please try again"

    }

    object Credentials {
        private const val PRIVATE_GRANT_TYPE = "client_credentials"
        private const val PRIVATE_CLIENT_ID = "8dwk3kbGjEoPvdDb08zReTo2oPIWPRJS5tUuayMaJ57qLWlBEO"
        private const val PRIVATE_CLIENT_SECRET = "fgCvRsYqsVXDt8uThQW1IUhL9YSWPrecGarVyTzG"

        const val GRANT_TYPE = PRIVATE_GRANT_TYPE
        const val CLIENT_ID = PRIVATE_CLIENT_ID
        const val CLIENT_SECRET = PRIVATE_CLIENT_SECRET

    }


}