package com.example.catganisation.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catganisation.domain.model.Breed

@Database(entities = [Breed::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao
}