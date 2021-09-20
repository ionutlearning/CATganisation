package com.example.catganisation.domain.usecase

import com.example.catganisation.domain.entities.Breed
import com.example.catganisation.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetBreedsTask @Inject constructor(
    private val repository: BreedsRepository
) {

    fun getBreeds(): Flow<List<Breed>> {
        return repository.getBreeds().transform { breeds -> if (breeds.isNotEmpty()) emit(breeds) }
    }

    fun getBreedsByOrigin(origin: String): Flow<List<Breed>> {
        return repository.getBreedsByOrigin(origin)
            .transform { breeds -> if (breeds.isNotEmpty()) emit(breeds) }
    }

    fun getBreedById(id: String): Flow<Breed> {
        return repository.getBreedById(id)
    }

}
