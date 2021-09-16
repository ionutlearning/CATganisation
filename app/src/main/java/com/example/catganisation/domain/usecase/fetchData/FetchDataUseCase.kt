package com.example.catganisation.domain.usecase.fetchData

import com.example.catganisation.domain.repository.FetchDataRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchDataUseCase @Inject constructor(
    private val repository: FetchDataRepository
) {

    operator fun invoke() = flow<Nothing> {
        repository.fetchData()
    }
}