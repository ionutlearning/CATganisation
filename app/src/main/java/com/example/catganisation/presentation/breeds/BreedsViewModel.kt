package com.example.catganisation.presentation.breeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.usecase.breeds.GetBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


@HiltViewModel
class BreedsViewModel @Inject constructor(getBreedsUseCase: GetBreedsUseCase) : ViewModel() {

    val viewState: LiveData<ViewResult<List<Breed>>> = getBreedsUseCase()
        .flowOn(Dispatchers.IO)
        .onStart { emit(ViewResult.Loading) }
        .catch { emit(ViewResult.Error(it.localizedMessage ?: "Unknown error")) }
        .asLiveData()
}