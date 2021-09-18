package com.example.catganisation.domain.usecase

import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.repository.LoginRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class LoginTask @Inject constructor(
    private val repository: LoginRepository
) {

    operator fun invoke(username: String, password: String) = flow {
        try {
            emit(ViewResult.Loading)
            val result = repository.login(username, password)
            if (result.status == 200) {
                emit(ViewResult.Success(true))

            } else {
                emit(ViewResult.Error("Unknown error"))
            }

        } catch (e: HttpException) {
            emit(ViewResult.Error(e.localizedMessage ?: "Http error"))
        }
    }
}