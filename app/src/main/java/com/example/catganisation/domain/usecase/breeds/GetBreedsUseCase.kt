package com.example.catganisation.domain.usecase.breeds

import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(
    private val repository: BreedsRepository
) {

    operator fun invoke(): Flow<ViewResult<List<Breed>>> {
        return repository.getBreeds()
            .transform { breeds -> if (breeds.isNotEmpty()) emit(ViewResult.Success(breeds)) }
    }

}
