package com.example.catganisation.data.mappers

import com.example.catganisation.data.remote.dto.BreedDto
import com.example.catganisation.data.remote.dto.LoginDto
import com.example.catganisation.domain.entities.BreedEntity
import com.example.catganisation.domain.entities.FilterEntity
import com.example.catganisation.domain.entities.LoginEntity

fun BreedDto.toBreed() = com.example.catganisation.data.local.model.BreedEntity(
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

fun com.example.catganisation.data.local.model.BreedEntity.toBreedEntity() = BreedEntity(
    id = id,
    name = name,
    description = description,
    imagePath = imagePath,
    origin = origin,
    temperament = temperament,
    link = link
)

fun com.example.catganisation.data.local.model.FilterEntity.toFilterEntity() = FilterEntity(
    name = name
)
