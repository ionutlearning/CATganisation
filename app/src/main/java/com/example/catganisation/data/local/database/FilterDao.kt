package com.example.catganisation.data.local.database

import androidx.room.*
import com.example.catganisation.data.local.model.FilterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilterDao {

    @Query("SELECT * FROM filters  ORDER BY name")
    fun getFilters(): Flow<List<FilterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(filters: List<FilterEntity>)

    @Query("DELETE FROM filters")
    suspend fun deleteAll()
}