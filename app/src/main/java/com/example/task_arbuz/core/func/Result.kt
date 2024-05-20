package com.example.task_arbuz.core.func

sealed class Result<out E, out D> {
    data class Error<out E>(val a: E): Result<E, Nothing>()
    data class Success<out D>(val b: D): Result<Nothing, D>()
}