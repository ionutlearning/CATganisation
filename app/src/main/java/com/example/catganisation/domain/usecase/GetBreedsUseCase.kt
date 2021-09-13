package com.example.catganisation.domain.usecase

import com.example.catganisation.data.remote.CatApi
import com.example.catganisation.data.remote.mappers.toBreed
import com.example.catganisation.domain.NetworkResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(
    private val catApi: CatApi
) {

    operator fun invoke() = flow {
        try {
            emit(NetworkResult.Loading)
            val breeds = catApi.getBreeds().map { it.toBreed() }
            emit(NetworkResult.Success(breeds))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage ?: "An unknown error occured"))
        }
    }
}