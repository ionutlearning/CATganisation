package com.example.catganisation.domain.usecase

import com.example.catganisation.domain.entities.BreedEntity
import com.example.catganisation.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetBreedsTask @Inject constructor(
    private val repository: BreedsRepository
) {

    fun getBreeds(): Flow<List<BreedEntity>> {
        return repository.getBreeds().transform { breeds -> if (breeds.isNotEmpty()) emit(breeds) }
    }

    fun getBreedsByOrigin(origin: String): Flow<List<BreedEntity>> {
        return repository.getBreedsByOrigin(origin)
            .transform { breeds -> if (breeds.isNotEmpty()) emit(breeds) }
    }

    fun getBreedById(id: String): Flow<BreedEntity> {
        println("aici123 UC ${id}")
        return repository.getBreedById(id)
    }

}
