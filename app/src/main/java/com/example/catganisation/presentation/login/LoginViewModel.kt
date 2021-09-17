package com.example.catganisation.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.ViewResult
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

    private val _state = MutableLiveData<ViewResult<Boolean>>()
    val state: LiveData<ViewResult<Boolean>> = _state

    fun login(username: String, password: String) {
        loginUseCase(username, password).onEach { result ->
            if (result is ViewResult.Success) {
                println("aici123 gooo")
                fetchDataUseCase()
            }
            _state.postValue(result)
        }.launchIn(viewModelScope)
    }
}