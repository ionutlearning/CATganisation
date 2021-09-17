package com.example.catganisation.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.NetworkResult
import com.example.catganisation.domain.usecase.fetchData.FetchDataUseCase
import com.example.catganisation.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val fetchDataUseCase: FetchDataUseCase
) : ViewModel() {

    private val _state = MutableLiveData<NetworkResult<Boolean>>()
    val state: LiveData<NetworkResult<Boolean>> = _state

    fun login(username: String, password: String) {
        loginUseCase(username, password).onEach { result ->
            if (result is NetworkResult.Success) {
                println("aici123 gooo")
                fetchDataUseCase()
            }
            _state.postValue(result)
        }.launchIn(viewModelScope)
    }
}