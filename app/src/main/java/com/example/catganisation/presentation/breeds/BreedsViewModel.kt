package com.example.catganisation.presentation.breeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.usecase.GetFiltersTask
import com.example.catganisation.domain.usecase.breeds.GetBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BreedsViewModel @Inject constructor(
    getBreedsTask: GetBreedsUseCase,
    getFiltersTask: GetFiltersTask
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewResult<BreedsViewState>>()
    val viewState = _viewState

    init {
        val exceptionHandler = CoroutineExceptionHandler { _, e ->
            _viewState.postValue(ViewResult.Error(e.localizedMessage ?: "Unknown error"))
        }

        _viewState.postValue(ViewResult.Loading)
        viewModelScope.launch(exceptionHandler) {
            getBreedsTask().combine(getFiltersTask()) { breeds, filters ->
                BreedsViewState(breeds, filters)
            }.collect { state ->
                _viewState.postValue(ViewResult.Success(state))
            }
        }
    }
}