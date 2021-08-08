package com.example.sary_app.utils

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val errMessage: String?) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
