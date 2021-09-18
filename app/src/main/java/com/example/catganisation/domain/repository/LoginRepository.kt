package com.example.catganisation.domain.repository

import com.example.catganisation.domain.model.LoginResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun login(username: String, password: String): Flow<LoginResult>
}