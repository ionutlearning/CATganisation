package com.example.catganisation.data.local.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.catganisation.data.local.database.BreedDao
import com.example.catganisation.data.remote.mappers.toBreed
import com.example.catganisation.data.remote.services.CatService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val service: CatService,
    private val dao: BreedDao
) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val breeds = service.getBreeds()
            println("aici breeds ${breeds.size}")

            breeds.map { breed ->
                dao.insertAll(
                    breed.toBreed()
                )
            }

            Result.success()

        } catch (e: Exception) {
            println("aici123 ${e.localizedMessage}")
            Result.failure()
        }
    }
}