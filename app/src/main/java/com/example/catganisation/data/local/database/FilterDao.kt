package com.example.catganisation.data.local.database

import androidx.room.*
import com.example.catganisation.domain.model.Filter

@Dao
interface FilterDao {

    @Query("SELECT * FROM filter")
    suspend fun getFilters(): List<Filter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(filters: List<Filter>)

    @Delete
    suspend fun delete(filter: Filter)
}