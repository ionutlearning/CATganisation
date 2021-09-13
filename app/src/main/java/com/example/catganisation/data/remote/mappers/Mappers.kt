package com.example.catganisation.data.remote.mappers

import com.example.catganisation.data.remote.dto.BreedDto
import com.example.catganisation.domain.model.Breed

fun BreedDto.toBreed() = Breed(
    name = name,
    description = description,
    imagePath = "https://cdnb.artstation.com/p/assets/images/images/008/897/787/large/weiss-hunt-ab8cbcd8dbb547028285e8d2d3b668f9.jpg"
)