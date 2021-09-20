package com.example.catganisation.domain.entities

data class Breed(
    val id: String,
    val name: String,
    val description: String,
    val imagePath: String,
    val origin: String,
    val temperament: String,
    val link: String
)