package com.example.catganisation.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.catganisation.domain.model.Breed

@Dao
interface BreedDao {
    @Query("SELECT * FROM breed")
    fun getAll(): List<Breed>

    @Query("SELECT * FROM breed WHERE id IS :id")
    fun getById(id: String): Breed

    @Insert
    fun insertAll(vararg breed: Breed)

    @Delete
    fun delete(breed: Breed)
}