package com.example.catganisation.domain.repository

import com.example.catganisation.domain.entities.Login
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun login(username: String, password: String): Flow<Login>
}