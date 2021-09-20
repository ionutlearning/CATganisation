package com.example.catganisation.data.local.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.catganisation.data.local.database.BreedDao
import com.example.catganisation.data.local.database.FilterDao
import com.example.catganisation.data.mappers.toBreed
import com.example.catganisation.data.remote.services.BreedsService
import com.example.catganisation.data.local.model.FilterEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val service: BreedsService,
    private val breedDao: BreedDao,
    private val filterDao: FilterDao
) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            resetTables()

            val countries = mutableListOf<String>()
            val breeds = service.getBreeds().map {
                countries.add(it.origin)
                it.toBreed()
            }
            breedDao.insertAll(breeds)

            val filters = countries.distinct().map { FilterEntity(it) }
            filterDao.insertAll(filters)

            Result.success()

        } catch (e: Exception) {
            Result.retry()
        }
    }

    private suspend fun resetTables() {
        breedDao.deleteAll()
        filterDao.deleteAll()
    }
}