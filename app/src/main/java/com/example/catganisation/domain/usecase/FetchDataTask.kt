package com.example.catganisation.domain.usecase

import com.example.catganisation.domain.repository.FetchDataRepository
import javax.inject.Inject

class FetchDataTask @Inject constructor(
    private val repository: FetchDataRepository
) {

    suspend operator fun invoke() {
        repository.fetchData()
    }
}