package com.example.catganisation.presentation.breeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.ViewResult
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

    private var cachedFilter = ALL_BREEDS

    init {
        val exceptionHandler = CoroutineExceptionHandler { _, e ->
            _viewState.postValue(ViewResult.Error(e.localizedMessage ?: "Unknown error"))
        }

        _viewState.postValue(ViewResult.Loading)
        viewModelScope.launch(exceptionHandler) {
            getBreedsTask.getBreeds().combine(getFiltersTask()) { breeds, filters ->
                BreedsViewState(breeds, filters)
            }.collect { state ->
                _viewState.postValue(ViewResult.Success(state))
            }
        }
    }

    fun filter(filter: String) {
        if (cachedFilter != filter) {
            println("aici filter $filter $cachedFilter")
            val exceptionHandler = CoroutineExceptionHandler { _, e ->
                _viewState.postValue(ViewResult.Error(e.localizedMessage ?: "Unknown error"))
            }

            _viewState.postValue(ViewResult.Loading)
            viewModelScope.launch(exceptionHandler) {

                val breedsFlow =
                    if (filter == ALL_BREEDS) {
                        getBreedsTask.getBreeds()
                    } else {
                        getBreedsTask.getBreedsByOrigin(filter)
                    }

                breedsFlow.collect { breeds ->
                    val state = BreedsViewState(breeds, isFiltering = true)
                    _viewState.postValue(ViewResult.Success(state))
                    cachedFilter = filter
                }
            }
        }
    }

    companion object {
        val ALL_BREEDS = "All Breeds"
    }
}