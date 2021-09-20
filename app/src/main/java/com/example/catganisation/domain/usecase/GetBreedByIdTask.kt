package com.example.catganisation.domain.usecase

import com.example.catganisation.domain.entities.Breed
import com.example.catganisation.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBreedByIdTask @Inject constructor(
    private val repository: BreedsRepository
) {

    operator fun invoke(id: String): Flow<Breed> {
        return repository.getBreedById(id)
    }

}
