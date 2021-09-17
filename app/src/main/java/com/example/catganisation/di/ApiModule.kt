package com.example.catganisation.di

import com.example.catganisation.data.remote.services.BreedsService
import com.example.catganisation.data.remote.mock.LoggingInterceptor
import com.example.catganisation.data.remote.services.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun getBreedsService(): BreedsService {
        return Retrofit.Builder()
            .baseUrl(BreedsService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BreedsService::class.java)
    }

    @Provides
    @Singleton
    fun getLoginService(client: OkHttpClient): LoginService {
        return Retrofit.Builder()
            .baseUrl(LoginService.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginService::class.java)
    }

    @Provides
    @Singleton
    fun getInterceptor(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor())
        .build()
}