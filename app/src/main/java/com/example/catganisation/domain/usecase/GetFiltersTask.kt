package com.example.catganisation.domain.usecase

import com.example.catganisation.domain.entities.Filter
import com.example.catganisation.domain.repository.FiltersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetFiltersTask @Inject constructor(
    private val repository: FiltersRepository
) {

    operator fun invoke(): Flow<List<Filter>> {
        return repository.getFilters()
            .transform { filters -> if (filters.isNotEmpty()) emit(filters) }
    }

}
