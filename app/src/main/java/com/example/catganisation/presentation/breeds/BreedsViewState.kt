package com.example.catganisation.presentation.breeds

import com.example.catganisation.domain.entities.BreedEntity
import com.example.catganisation.domain.entities.FilterEntity

data class BreedsViewState(
    val breeds: List<BreedEntity>,
    val filters: List<FilterEntity> = emptyList(),
    val filter: String,
    val isFiltering: Boolean = false
)