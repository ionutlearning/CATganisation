package com.example.catganisation.data.repository

import com.example.catganisation.data.remote.LoginService
import com.example.catganisation.data.remote.mappers.toLoginResult
import com.example.catganisation.domain.model.LoginResult
import com.example.catganisation.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val service: LoginService) : LoginRepository {

    override suspend fun login(username: String, password: String): LoginResult {
        return service.login(username, password).toLoginResult()
    }
}