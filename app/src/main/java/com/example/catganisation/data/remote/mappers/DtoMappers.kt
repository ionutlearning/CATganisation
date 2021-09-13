package com.example.catganisation.data.remote.mappers

import com.example.catganisation.data.remote.dto.BreedDto
import com.example.catganisation.domain.model.Breed

fun BreedDto.toBreed() = Breed(
    name = name,
    description = description,
    imagePath = image?.url ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png"
)