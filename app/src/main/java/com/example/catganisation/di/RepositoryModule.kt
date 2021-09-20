package com.example.catganisation.di

import com.example.catganisation.data.repository.*
import com.example.catganisation.domain.repository.*
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

    @Binds
    abstract fun bindFiltersRepository(
        impl: FiltersRepositoryImpl
    ): FiltersRepository

    @Binds
    abstract fun bindCachedPrefsRepository(
        impl: CachedPrefsRepositoryImpl
    ): CachedPrefsRepository
}