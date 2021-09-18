package com.example.catganisation.domain.repository

import com.example.catganisation.domain.model.Filter
import kotlinx.coroutines.flow.Flow

interface FiltersRepository {

    fun getFilters(): Flow<List<Filter>>
}