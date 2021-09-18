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

    @Query("SELECT * FROM breed WHERE roomId IS :id")
    fun getBreedById(id: Int): Flow<Breed>

    @Query("DELETE FROM breed")
    suspend fun deleteAll()
}