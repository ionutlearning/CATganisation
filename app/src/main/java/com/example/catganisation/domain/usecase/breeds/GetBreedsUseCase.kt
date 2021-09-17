package com.example.catganisation.domain.usecase.breeds

import com.example.catganisation.domain.repository.BreedsRepository
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(
    private val repository: BreedsRepository
) {

    operator fun invoke() = repository.getBreeds()
}
