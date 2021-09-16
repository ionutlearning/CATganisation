package com.example.catganisation.di

import com.example.catganisation.data.repository.BreedsRepositoryImpl
import com.example.catganisation.data.repository.FetchDataRepositoryImpl
import com.example.catganisation.data.repository.LoginRepositoryImpl
import com.example.catganisation.domain.repository.BreedsRepository
import com.example.catganisation.domain.repository.FetchDataRepository
import com.example.catganisation.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBreedRepository(
        impl: BreedsRepositoryImpl
    ): BreedsRepository


    @Binds
    abstract fun bindLoginRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    abstract fun bindFetchDataRepository(
        impl: FetchDataRepositoryImpl
    ): FetchDataRepository
}