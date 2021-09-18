package com.example.catganisation.presentation.breeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.model.Filter
import com.example.catganisation.domain.usecase.GetBreedsTask
import com.example.catganisation.domain.usecase.GetFiltersTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getBreedsTask: GetBreedsTask,
    getFiltersTask: GetFiltersTask
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewResult<BreedsViewState>>()
    val viewState = _viewState

    private var cachedFilters = emptyList<Filter>()

    init {
        val exceptionHandler = CoroutineExceptionHandler { _, e ->
            _viewState.postValue(ViewResult.Error(e.localizedMessage ?: "Unknown error"))
        }

        _viewState.postValue(ViewResult.Loading)
        viewModelScope.launch(exceptionHandler) {
            getBreedsTask.getBreeds().combine(getFiltersTask()) { breeds, filters ->
                cachedFilters = filters
                BreedsViewState(breeds, filters)
            }.collect { state ->
                _viewState.postValue(ViewResult.Success(state))
            }
        }
    }

    fun filter(filter: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, e ->
            _viewState.postValue(ViewResult.Error(e.localizedMessage ?: "Unknown error"))
        }

        _viewState.postValue(ViewResult.Loading)
        viewModelScope.launch(exceptionHandler) {
            if (filter == ALL_BREEDS) {
                getBreedsTask.getBreeds()
                    .collect { breeds ->
                        val state = BreedsViewState(breeds, cachedFilters)
                        _viewState.postValue(ViewResult.Success(state))
                    }

            } else {
                getBreedsTask.getBreedsByOrigin(filter)
                    .collect { breeds ->
                        val state = BreedsViewState(breeds, cachedFilters)
                        _viewState.postValue(ViewResult.Success(state))
                    }
            }
        }
    }

    companion object {
        val ALL_BREEDS = "All Breeds"
    }
}