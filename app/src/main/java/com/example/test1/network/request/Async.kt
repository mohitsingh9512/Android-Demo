package com.example.test1.network.request

sealed class Async<out T> {

    data object Loading: Async<Nothing>()

    data class Error(val errorCode: Int): Async<Nothing>()

    data class Success<out T>(val data: T): Async<T>()

    data object None: Async<Nothing>()
}