package com.example.catganisation.data.mappers

import com.example.catganisation.data.remote.dto.BreedDto
import com.example.catganisation.data.remote.dto.LoginDto
import com.example.catganisation.data.local.model.Breed
import com.example.catganisation.data.local.model.Filter
import com.example.catganisation.domain.entities.BreedEntity
import com.example.catganisation.domain.entities.FilterEntity
import com.example.catganisation.domain.entities.LoginEntity

fun BreedDto.toBreed() = Breed(
    id = id,
    name = name,
    description = description,
    imagePath = image?.url
        ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png",
    origin = origin,
    temperament = temperament,
    link = wikipediaUrl ?: "no url"
)

fun LoginDto.toLoginResult() = LoginEntity(
    status = status,
    message = message
)

fun Breed.toBreedEntity() = BreedEntity(
    id = id,
    name = name,
    description = description,
    imagePath = imagePath,
    origin = origin,
    temperament = temperament,
    link = link
)

fun Filter.toFilterEntity() = FilterEntity(
    name = name
)
