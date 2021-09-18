package com.example.catganisation.data.repository

import com.example.catganisation.data.local.database.BreedDao
import com.example.catganisation.data.mappers.toBreedEntity
import com.example.catganisation.domain.entities.BreedEntity
import com.example.catganisation.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(private val breedDao: BreedDao) : BreedsRepository {

    override fun getBreeds(): Flow<List<BreedEntity>> {
        return breedDao.getBreeds().transform { emit(it.map { it.toBreedEntity() }) }
    }

    override fun getBreedsByOrigin(origin: String): Flow<List<BreedEntity>> {
        return breedDao.getBreedByOrigin(origin).transform { emit(it.map { it.toBreedEntity() }) }
    }

    override fun getBreedById(id: String): Flow<BreedEntity> {
        println("aici123 rep impl ${id}")
        return breedDao.getBreedById(id).transform { emit(it.toBreedEntity()) }
    }
}