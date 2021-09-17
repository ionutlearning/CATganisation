package com.example.catganisation.data.repository

import com.example.catganisation.data.remote.services.BreedsService
import com.example.catganisation.data.remote.mappers.toBreed
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.repository.BreedsRepository
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(private val service: BreedsService) : BreedsRepository {

    override suspend fun getBreeds(): List<Breed> {
        return service.getBreeds().map { it.toBreed() }
    }
}