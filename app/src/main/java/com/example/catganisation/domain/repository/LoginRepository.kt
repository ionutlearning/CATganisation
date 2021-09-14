package com.example.catganisation.domain.repository

import com.example.catganisation.domain.model.LoginResult

interface LoginRepository {

    suspend fun login(username: String, password: String): LoginResult
}