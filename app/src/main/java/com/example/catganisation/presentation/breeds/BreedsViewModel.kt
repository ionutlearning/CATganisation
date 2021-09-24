package com.example.catganisation.presentation.breeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.entities.Filter
import com.example.catganisation.domain.usecase.GetBreedsByOriginTask
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
    private val getBreedsByOriginTask: GetBreedsByOriginTask,
    private val getFiltersTask: GetFiltersTask
) : ViewModel() {

    private var cachedFilters = emptyList<Filter>()
    private val _viewState = MutableLiveData<ViewResult<BreedsViewState>>()

    val viewState: LiveData<ViewResult<BreedsViewState>> = _viewState
    var cachedFilter = ALL_BREEDS

    init {
        val exceptionHandler = CoroutineExceptionHandler { _, e ->
            _viewState.postValue(ViewResult.Error(e.localizedMessage ?: "Unknown error"))
        }

        _viewState.postValue(ViewResult.Loading)
        viewModelScope.launch(exceptionHandler) {
            getBreedsTask().combine(getFiltersTask()) { breeds, filters ->
                cachedFilters = filters
                BreedsViewState(breeds, cachedFilters, ALL_BREEDS)
            }.collect { state ->
                _viewState.postValue(ViewResult.Success(state))
            }
        }
    }

    fun filter(filter: String) {
        if (cachedFilter != filter) {
            val exceptionHandler = CoroutineExceptionHandler { _, e ->
                _viewState.postValue(ViewResult.Error(e.localizedMessage ?: "Unknown error"))
            }

            _viewState.postValue(ViewResult.Loading)
            viewModelScope.launch(exceptionHandler) {

                val breedsFlow =
                    if (filter == ALL_BREEDS) {
                        getBreedsTask()
                    } else {
                        getBreedsByOriginTask(filter)
                    }

                breedsFlow.collect { breeds ->
                    val state = BreedsViewState(breeds, cachedFilters, filter = filter)
                    _viewState.postValue(ViewResult.Success(state))
                    cachedFilter = filter
                }
            }
        }
    }

    companion object {
        const val ALL_BREEDS = "All Breeds"
    }
}