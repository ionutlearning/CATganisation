package com.example.catganisation

import com.example.catganisation.domain.entities.Breed
import com.example.catganisation.domain.entities.Filter
import com.example.catganisation.presentation.breeds.BreedsViewState

val breed = Breed(
    "1",
    "name",
    "description",
    "imagePath",
    "origin",
    "temperament",
    "link"
)

val filter = Filter("filter")

val errorMessage = "error"

val filterFrance = "France"

val filterAllBreeds = "All Breeds"

val filteredFranceViewState = BreedsViewState(listOf(breed), emptyList(), filterFrance)

val filteredAllBreedsViewState = BreedsViewState(listOf(breed), emptyList(), filterAllBreeds)