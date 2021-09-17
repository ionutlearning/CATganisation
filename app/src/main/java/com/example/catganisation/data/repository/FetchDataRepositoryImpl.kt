package com.example.catganisation.data.repository

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.catganisation.data.local.worker.FetchWorker
import com.example.catganisation.domain.repository.FetchDataRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FetchDataRepositoryImpl @Inject constructor(
    @ApplicationContext private val appContext: Context,
) : FetchDataRepository {

    override suspend fun fetchData() {
        println("aici123 start fetch")
        val request = OneTimeWorkRequestBuilder<FetchWorker>()
            .build()
        WorkManager.getInstance(appContext).enqueue(request)
    }
}
