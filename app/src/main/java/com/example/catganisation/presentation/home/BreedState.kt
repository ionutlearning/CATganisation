package com.example.catganisation.presentation.home

import com.example.catganisation.domain.model.Breed

data class BreedState(
    val isLoading: Boolean = false,
    val data: List<Breed> = emptyList(),
    val error: String? = null
)