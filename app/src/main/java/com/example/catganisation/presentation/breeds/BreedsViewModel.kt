package com.example.catganisation.presentation.breeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.usecase.breeds.GetBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BreedsViewModel @Inject constructor(getBreedsUseCase: GetBreedsUseCase) : ViewModel() {

    private val _viewState = MutableLiveData<ViewResult<List<Breed>>>()
    val viewState = _viewState

    init {
        val exceptionHandler = CoroutineExceptionHandler { _, e ->
            _viewState.postValue(ViewResult.Error(e.localizedMessage ?: "Unknown error"))
        }

        _viewState.postValue(ViewResult.Loading)
        viewModelScope.launch(exceptionHandler) {
            getBreedsUseCase().collect { breeds ->
                _viewState.postValue(ViewResult.Success(breeds))
            }
        }
    }
}