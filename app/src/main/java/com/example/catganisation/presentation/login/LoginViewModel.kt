package com.example.catganisation.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.usecase.FetchDataTask
import com.example.catganisation.domain.usecase.LoginTask
import com.example.catganisation.presentation.ui.util.ConnectionHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginTask: LoginTask,
    private val fetchDataTask: FetchDataTask,
    private val connectionHelper: ConnectionHelper
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewResult<Boolean>>()
    val viewState: LiveData<ViewResult<Boolean>> = _viewState

    fun login(username: String, password: String) {
        _viewState.postValue(ViewResult.Loading)
        if (connectionHelper.isConnectedToInternet()) {
            val exceptionHandler = CoroutineExceptionHandler { _, e ->
                _viewState.postValue(ViewResult.Error(e.localizedMessage ?: "Unknown error"))
            }

            viewModelScope.launch(exceptionHandler) {
                loginTask(username, password).collect { result ->
                    if (result.status == 200) {
                        fetchDataTask()
                        _viewState.postValue(ViewResult.Success(true))
                    }
                }
            }

        } else {
            _viewState.postValue(ViewResult.Error("No Internet Connection"))
        }
    }
}