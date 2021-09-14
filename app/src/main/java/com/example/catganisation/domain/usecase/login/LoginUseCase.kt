package com.example.catganisation.domain.usecase.login

import com.example.catganisation.domain.NetworkResult
import com.example.catganisation.domain.repository.LoginRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    operator fun invoke(username: String, password: String) = flow {
        try {
            emit(NetworkResult.Loading)
            val result = repository.login(username, password)
            if (result.status == 200) {
                emit(NetworkResult.Success(true))

            } else {
                emit(NetworkResult.Error("Unknown error"))
            }

        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: "Http error"))
        }
    }
}