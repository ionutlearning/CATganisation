package com.example.catganisation.data.local.database

import androidx.room.*
import com.example.catganisation.domain.model.Breed
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDao {
    @Query("SELECT * FROM breed")
    fun getBreeds(): Flow<List<Breed>>

    @Query("SELECT * FROM breed WHERE id IS :id")
    suspend fun getBreedById(id: String): Breed

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(breed: List<Breed>)

    @Delete
    suspend fun delete(breed: Breed)
}