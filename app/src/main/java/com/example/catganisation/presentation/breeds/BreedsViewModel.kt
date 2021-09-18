package com.example.catganisation.presentation.breeds

import androidx.lifecycle.*
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.usecase.breeds.GetBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BreedsViewModel @Inject constructor(useCase: GetBreedsUseCase) : ViewModel() {

    val state: LiveData<ViewResult<List<Breed>>> = useCase()
        .flowOn(Dispatchers.IO)
        .onStart { emit(ViewResult.Loading) }
        .catch { emit(ViewResult.Error(it.localizedMessage ?: "Unknown error")) }
        .asLiveData()
}