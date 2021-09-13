package com.example.catganisation.domain.usecase

import com.example.catganisation.domain.NetworkResult
import com.example.catganisation.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(
    private val repository: BreedsRepository
) {

    operator fun invoke() = flow {
        try {
            emit(NetworkResult.Loading)
            val breeds = repository.getBreeds()
            emit(NetworkResult.Success(breeds))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage ?: "An unknown error occured"))
        }
    }
}