package com.example.catganisation.domain.repository

import com.example.catganisation.domain.entities.BreedEntity
import kotlinx.coroutines.flow.Flow

interface BreedsRepository {

    fun getBreeds(): Flow<List<BreedEntity>>

    fun getBreedsByOrigin(origin: String): Flow<List<BreedEntity>>

    fun getBreedById(id: String): Flow<BreedEntity>
}