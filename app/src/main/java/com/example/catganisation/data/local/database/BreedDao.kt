package com.example.catganisation.data.local.database

import androidx.room.*
import com.example.catganisation.domain.model.Breed

@Dao
interface BreedDao {
    @Query("SELECT * FROM breed")
    suspend fun getBreeds(): List<Breed>

    @Query("SELECT * FROM breed WHERE id IS :id")
    suspend fun getBreedById(id: String): Breed

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(breed: List<Breed>)

    @Delete
    suspend fun delete(breed: Breed)
}