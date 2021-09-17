package com.example.catganisation.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Filter(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var roomId: Int = 0
}