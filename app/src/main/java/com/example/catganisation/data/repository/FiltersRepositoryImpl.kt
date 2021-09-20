package com.example.catganisation.data.repository

import com.example.catganisation.data.local.database.FilterDao
import com.example.catganisation.data.mappers.toFilterEntity
import com.example.catganisation.domain.entities.Filter
import com.example.catganisation.domain.repository.FiltersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class FiltersRepositoryImpl @Inject constructor(private val filterDao: FilterDao) :
    FiltersRepository {

    override fun getFilters(): Flow<List<Filter>> =
        filterDao.getFilters().transform { emit(it.map { it.toFilterEntity() }) }
}