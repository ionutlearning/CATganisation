package com.example.catganisation.data.remote.services

import com.example.catganisation.data.remote.dto.BreedDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface CatService {

    @Headers("x-api-key:$API_KEY")
    @GET("/v1/breeds")
    suspend fun getBreeds(): List<BreedDto>

    companion object {
        const val API_KEY = "1b423d6c-98a7-4205-a8ad-4b68b5f2b971"
        const val BASE_URL = "https://api.thecatapi.com"
    }
}
