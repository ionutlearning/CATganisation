package com.example.catganisation.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.usecase.GetBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(useCase: GetBreedsUseCase) : ViewModel() {

    private val _state = BreedState()
    val state: LiveData<BreedState> = liveData { _state }

    init {
        useCase().onEach {
            println(it)
        }.launchIn(viewModelScope)
    }
}