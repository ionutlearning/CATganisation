package com.example.catganisation.presentation.breeds

import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.model.Filter

data class BreedsViewState(
    val breeds: List<Breed>,
    val filters: List<Filter> = emptyList(),
    val isFiltering: Boolean = false
)