package com.example.catganisation.di

import android.content.Context
import androidx.room.Room
import com.example.catganisation.data.local.database.AppDatabase
import com.example.catganisation.data.local.database.BreedDao
import com.example.catganisation.data.local.database.FilterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "CatDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBreedDao(appDatabase: AppDatabase): BreedDao {
        return appDatabase.breedDao()
    }

    @Provides
    @Singleton
    fun provideFilterDao(appDatabase: AppDatabase): FilterDao {
        return appDatabase.filterDao()
    }
}