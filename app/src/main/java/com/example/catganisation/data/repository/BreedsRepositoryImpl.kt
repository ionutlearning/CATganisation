package com.example.catganisation.data.repository

import com.example.catganisation.data.remote.CatApi
import com.example.catganisation.data.remote.mappers.toBreed
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.repository.BreedsRepository
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(private val api: CatApi) : BreedsRepository {

    override suspend fun getBreeds(): List<Breed> {
        return api.getBreeds().map { it.toBreed() }
    }
}