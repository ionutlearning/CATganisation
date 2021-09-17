package com.example.catganisation.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Breed(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    @ColumnInfo(name = "image_path") val imagePath: String
)