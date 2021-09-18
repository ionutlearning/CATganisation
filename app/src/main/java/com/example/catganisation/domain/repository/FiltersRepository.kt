package com.example.catganisation.domain.repository

import com.example.catganisation.domain.entities.FilterEntity
import kotlinx.coroutines.flow.Flow

interface FiltersRepository {

    fun getFilters(): Flow<List<FilterEntity>>
}