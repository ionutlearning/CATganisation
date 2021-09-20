package com.example.catganisation.presentation.breeds

import com.example.catganisation.domain.entities.Breed
import com.example.catganisation.domain.entities.Filter

data class BreedsViewState(
    val breeds: List<Breed>,
    val filters: List<Filter> = emptyList(),
    val filter: String,
    val isFiltering: Boolean = false
)