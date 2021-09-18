package com.example.catganisation.data.local.database

import androidx.room.*
import com.example.catganisation.domain.model.Breed
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDao {
    @Query("SELECT * FROM breed ORDER BY name")
    fun getBreeds(): Flow<List<Breed>>

    @Query("SELECT * FROM breed WHERE origin IS :origin ORDER BY name")
    fun getBreedByOrigin(origin: String): Flow<List<Breed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(breed: List<Breed>)

    @Delete
    suspend fun delete(breed: Breed)
}