package com.example.catganisation.data.repository

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
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
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = OneTimeWorkRequestBuilder<FetchWorker>()
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(appContext).enqueue(request)
    }
}
