package com.example.catganisation.domain.repository

import com.example.catganisation.domain.model.Breed
import kotlinx.coroutines.flow.Flow

interface BreedsRepository {

    fun getBreeds() : Flow<List<Breed>>
}