package com.example.catganisation.domain.repository

import com.example.catganisation.domain.model.Breed

interface BreedsRepository {

    suspend fun getBreeds() : List<Breed>
}