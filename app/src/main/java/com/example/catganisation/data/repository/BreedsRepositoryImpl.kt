package com.example.catganisation.data.repository

import com.example.catganisation.data.local.database.BreedDao
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(private val breedDao: BreedDao) : BreedsRepository {

    override fun getBreeds(): Flow<List<Breed>> {
        return breedDao.getBreeds()
    }

    override fun getBreedsByOrigin(origin: String): Flow<List<Breed>> {
        return breedDao.getBreedByOrigin(origin)
    }
}