package com.example.catganisation.data.mappers

import com.example.catganisation.data.local.model.BreedEntity
import com.example.catganisation.data.local.model.FilterEntity
import com.example.catganisation.data.remote.dto.BreedDto
import com.example.catganisation.data.remote.dto.LoginDto
import com.example.catganisation.domain.entities.Breed
import com.example.catganisation.domain.entities.Filter
import com.example.catganisation.domain.entities.Login

fun BreedDto.toBreed() = BreedEntity(
    id = id,
    name = name,
    description = description,
    imagePath = image?.url
        ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png",
    origin = origin,
    temperament = temperament,
    link = wikipediaUrl ?: "no url"
)

fun LoginDto.toLoginResult() = Login(
    status = status,
    message = message
)

fun BreedEntity.toBreedEntity() = Breed(
    id = id,
    name = name,
    description = description,
    imagePath = imagePath,
    origin = origin,
    temperament = temperament,
    link = link
)

fun FilterEntity.toFilterEntity() = Filter(
    name = name
)
