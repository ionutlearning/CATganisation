package com.example.catganisation.domain.usecase

import com.example.catganisation.data.remote.CatApi
import com.example.catganisation.data.remote.mappers.toBreed
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(
    private val catApi: CatApi
) {

    operator fun invoke() = flow {
        emit(catApi.getBreeds().map { it.toBreed() })
    }
}