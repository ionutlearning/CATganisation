package com.example.catganisation.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.entities.Breed
import com.example.catganisation.domain.usecase.GetBreedByIdTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getBreedByIdTask: GetBreedByIdTask
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewResult<Breed>>()
    val viewState: LiveData<ViewResult<Breed>> = _viewState

    fun getBreedById(id: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, e ->
            _viewState.postValue(ViewResult.Error(e.localizedMessage ?: "Unknown error"))
        }

        _viewState.postValue(ViewResult.Loading)
        viewModelScope.launch(exceptionHandler) {
            getBreedByIdTask(id).collect { breed ->
                _viewState.postValue(ViewResult.Success(breed))
            }
        }
    }
}