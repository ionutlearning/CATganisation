package com.example.catganisation

import com.example.catganisation.domain.entities.BreedEntity
import com.example.catganisation.domain.entities.FilterEntity
import com.example.catganisation.presentation.breeds.BreedsViewState

val breedEntity = BreedEntity(
    "1",
    "name",
    "description",
    "imagePath",
    "origin",
    "temperament",
    "link"
)

val filterEntity = FilterEntity("filter")

val errorMessage = "error"

val filterFrance = "France"

val filterAllBreeds = "All Breeds"

val filteredFranceViewState = BreedsViewState(listOf(breedEntity), emptyList(), filterFrance, true)

val filteredAllBreedsViewState = BreedsViewState(listOf(breedEntity), emptyList(), filterAllBreeds, true)