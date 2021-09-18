package com.example.catganisation.domain.usecase

import com.example.catganisation.domain.repository.LoginRepository
import javax.inject.Inject

class LoginTask @Inject constructor(
    private val repository: LoginRepository
) {

    suspend operator fun invoke(username: String, password: String) = repository.login(username, password)
}