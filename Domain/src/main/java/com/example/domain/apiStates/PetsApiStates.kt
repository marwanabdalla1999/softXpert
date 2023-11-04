package com.example.domain.apiStates

import com.example.domain.models.PetsModel

sealed class PetsApiStates {
    data object Idle : PetsApiStates()

    data object Loading : PetsApiStates()

    class Success(var data: PetsModel?) : PetsApiStates()
    class Failure(var error: Throwable, var offlineData: PetsModel?) : PetsApiStates()


}



