package com.example.catganisation.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filters")
data class FilterEntity(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var roomId: Int = 0
}