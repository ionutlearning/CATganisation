package com.example.catganisation.domain

sealed class ViewResult<out R> {
    object Loading : ViewResult<Nothing>()
    data class Success<out T>(val data: T) : ViewResult<T>()
    data class Error(val message: String) : ViewResult<Nothing>()
}
