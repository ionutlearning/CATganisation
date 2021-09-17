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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BreedsViewModel @Inject constructor(useCase: GetBreedsUseCase) : ViewModel() {

    private val _state = MutableLiveData<NetworkResult<List<Breed>>>()
    val state: LiveData<NetworkResult<List<Breed>>> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCase()
                .onStart { _state.postValue(NetworkResult.Loading) }
                .catch {
                    _state.postValue(
                        NetworkResult.Error(
                            it.localizedMessage ?: "Unknown error"
                        )
                    )
                }
                .collect { _state.postValue(NetworkResult.Success(it)) }
        }
    }
}