package com.example.catganisation.data.local.database

import androidx.room.*
import com.example.catganisation.domain.model.Filter
import kotlinx.coroutines.flow.Flow

@Dao
interface FilterDao {

    @Query("SELECT * FROM filter  ORDER BY name")
    fun getFilters(): Flow<List<Filter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(filters: List<Filter>)

    @Delete
    suspend fun delete(filter: Filter)
}