package com.example.catganisation.data.remote.mappers

import com.example.catganisation.data.remote.dto.BreedDto
import com.example.catganisation.data.remote.dto.LoginDto
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.model.LoginResult

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

fun LoginDto.toLoginResult() = LoginResult(
    status = status,
    message = message
)
