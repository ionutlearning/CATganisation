package com.example.catganisation.presentation.breeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.NetworkResult
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.usecase.breeds.GetBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BreedsViewModel @Inject constructor(useCase: GetBreedsUseCase) : ViewModel() {

    private val _state = MutableLiveData<NetworkResult<List<Breed>>>()
    val state: LiveData<NetworkResult<List<Breed>>> = _state

    init {
        _state.postValue(NetworkResult.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            useCase().collect { _state.postValue(it) }
        }
    }
}