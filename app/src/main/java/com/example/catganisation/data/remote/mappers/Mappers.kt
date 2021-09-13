package com.example.catganisation.data.remote.mappers

import com.example.catganisation.data.remote.dto.BreedDto
import com.example.catganisation.domain.model.Breed

fun BreedDto.toBreed() = Breed(name = name)