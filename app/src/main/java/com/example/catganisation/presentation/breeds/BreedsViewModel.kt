package com.example.catganisation.presentation.breeds

import androidx.lifecycle.*
import com.example.catganisation.domain.usecase.GetBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class BreedsViewModel @Inject constructor(useCase: GetBreedsUseCase) : ViewModel() {

    private val _state = MutableLiveData<BreedState>()
    val state: LiveData<BreedState> = _state

    init {
        useCase().onEach {
            _state.postValue(BreedState(data = it))
        }.launchIn(viewModelScope)
    }
}