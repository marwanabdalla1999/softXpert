package com.example.softxpert.petsHomeScreen.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.apiStates.PetsApiStates
import com.example.domain.useCases.getPets.IPetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsViewModel@Inject constructor(private val getPets:IPetsUseCase) :ViewModel() {

    private val _pets = MutableStateFlow<PetsApiStates>(PetsApiStates.Idle)
    val pets: StateFlow<PetsApiStates> = _pets
    private val _morePets = MutableStateFlow<PetsApiStates>(PetsApiStates.Idle)
    val morePets: StateFlow<PetsApiStates> = _morePets
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _pets.value = PetsApiStates.Failure(throwable,null)

    }



    fun getPets(type:String){

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            _pets.value = getPets.getPets(type)

        }

    }
    fun loadMorePets(currentPage:Int,pagesLimit:Int,type:String){

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            _morePets.value = getPets.loadMorePets(currentPage,pagesLimit,type)


        }

    }

}