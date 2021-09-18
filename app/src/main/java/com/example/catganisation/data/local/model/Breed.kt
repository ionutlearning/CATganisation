package com.example.catganisation.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Breed(
    val id: String,
    val name: String,
    val description: String,
    @ColumnInfo(name = "image_path") val imagePath: String,
    val origin: String,
    val temperament: String,
    val link: String
) {
    @PrimaryKey(autoGenerate = true)
    var roomId: Int = 0
}