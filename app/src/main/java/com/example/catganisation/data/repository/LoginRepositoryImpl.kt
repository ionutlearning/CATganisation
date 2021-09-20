package com.example.catganisation.data.repository

import com.example.catganisation.data.mappers.toLoginResult
import com.example.catganisation.data.remote.services.LoginService
import com.example.catganisation.domain.entities.Login
import com.example.catganisation.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val service: LoginService) : LoginRepository {

    override suspend fun login(username: String, password: String): Flow<Login> = flow {
        emit(service.login(username, password).toLoginResult())
    }.flowOn(Dispatchers.IO)
}