package com.example.catganisation.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.usecase.GetBreedsTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getBreedsTask: GetBreedsTask
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewResult<Breed>>()
    val viewState = _viewState

    fun getBreed(id: String) {
        println("aici123 details view getBreed ${id}")
        val exceptionHandler = CoroutineExceptionHandler { _, e ->
            _viewState.postValue(ViewResult.Error(e.localizedMessage ?: "Unknown error"))
        }

        _viewState.postValue(ViewResult.Loading)
        viewModelScope.launch(exceptionHandler) {
            getBreedsTask.getBreedById(id).collect { breed ->
                println("aici123 success ${breed}")
                _viewState.postValue(ViewResult.Success(breed))
            }
        }
    }
}