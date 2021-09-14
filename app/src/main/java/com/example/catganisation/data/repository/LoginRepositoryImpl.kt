package com.example.catganisation.data.repository

import com.example.catganisation.data.remote.LoginApi
import com.example.catganisation.data.remote.mappers.toLoginResult
import com.example.catganisation.domain.model.LoginResult
import com.example.catganisation.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val api: LoginApi) : LoginRepository {

    override suspend fun login(username: String, password: String): LoginResult {
        return api.login(username, password).toLoginResult()
    }
}