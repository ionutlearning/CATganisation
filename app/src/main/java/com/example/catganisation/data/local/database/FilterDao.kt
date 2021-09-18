package com.example.catganisation.data.local.database

import androidx.room.*
import com.example.catganisation.data.local.model.Filter
import kotlinx.coroutines.flow.Flow

@Dao
interface FilterDao {

    @Query("SELECT * FROM filter  ORDER BY name")
    fun getFilters(): Flow<List<Filter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(filters: List<Filter>)

    @Query("DELETE FROM filter")
    suspend fun deleteAll()
}