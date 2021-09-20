package com.example.catganisation.domain.usecase

import com.example.catganisation.domain.entities.Breed
import com.example.catganisation.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetBreedsByOriginTask @Inject constructor(
    private val repository: BreedsRepository
) {

    operator fun invoke(origin: String): Flow<List<Breed>> {
        return repository.getBreedsByOrigin(origin)
            .transform { breeds -> if (breeds.isNotEmpty()) emit(breeds) }
    }
}
