package com.example.catganisation.data.repository

import com.example.catganisation.data.local.database.FilterDao
import com.example.catganisation.domain.model.Filter
import com.example.catganisation.domain.repository.FiltersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FiltersRepositoryImpl @Inject constructor(private val filterDao: FilterDao) :
    FiltersRepository {

    override fun getFilters(): Flow<List<Filter>> = filterDao.getFilters()
}