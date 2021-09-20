package com.example.catganisation.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catganisation.data.local.model.BreedEntity
import com.example.catganisation.data.local.model.FilterEntity

@Database(entities = [BreedEntity::class, FilterEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao
    abstract fun filterDao(): FilterDao
}