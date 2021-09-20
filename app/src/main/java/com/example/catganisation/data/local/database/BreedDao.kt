package com.example.catganisation.data.local.database

import androidx.room.*
import com.example.catganisation.data.local.model.BreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDao {
    @Query("SELECT * FROM breeds ORDER BY name")
    fun getBreeds(): Flow<List<BreedEntity>>

    @Query("SELECT * FROM breeds WHERE origin IS :origin ORDER BY name")
    fun getBreedByOrigin(origin: String): Flow<List<BreedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(breed: List<BreedEntity>)

    @Query("SELECT * FROM breeds WHERE id IS :id")
    fun getBreedById(id: String): Flow<BreedEntity>

    @Query("DELETE FROM breeds")
    suspend fun deleteAll()
}