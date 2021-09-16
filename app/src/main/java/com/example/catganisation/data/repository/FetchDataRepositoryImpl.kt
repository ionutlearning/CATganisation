package com.example.catganisation.data.repository

import com.example.catganisation.domain.repository.FetchDataRepository
import javax.inject.Inject

class FetchDataRepositoryImpl @Inject constructor() : FetchDataRepository {

    override suspend fun fetchData() {
        // create work manager
        // wm has cat service injected
        // doWork call for cats - result is saved to DB - retry if failed



//        val breeds = service.getBreeds().map { it.toBreed() }
        // save to db

    }
}
