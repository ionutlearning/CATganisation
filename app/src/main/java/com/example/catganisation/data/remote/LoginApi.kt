package com.example.catganisation.data.remote

import com.example.catganisation.data.remote.dto.LoginDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginDto

    companion object {
        const val BASE_URL = "https://example.com"
    }
}
