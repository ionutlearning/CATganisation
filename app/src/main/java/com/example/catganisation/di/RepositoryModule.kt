package com.example.catganisation.di

import com.example.catganisation.data.repository.BreedsRepositoryImpl
import com.example.catganisation.domain.repository.BreedsRepository
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
}