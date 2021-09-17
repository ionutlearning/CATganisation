package com.example.catganisation.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.domain.model.Filter

@Database(entities = [Breed::class, Filter::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao
    abstract fun filterDao(): FilterDao
}