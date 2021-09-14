package com.example.catganisation.domain.usecase

import com.example.catganisation.domain.NetworkResult
import com.example.catganisation.domain.repository.LoginRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    operator fun invoke(username: String, password: String) = flow {
        emit(NetworkResult.Loading)
        val result = repository.login(username, password)
        if (result.status == 200) {
            emit(NetworkResult.Success(true))

        } else {
            emit(NetworkResult.Error(result.message))
        }
    }
}