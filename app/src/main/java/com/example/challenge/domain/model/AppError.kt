package com.example.challenge.domain.model

sealed class AppError {
    object NetworkError : AppError()
    object DatabaseError : AppError()
    data class ServerError(val code: Int, val message: String) : AppError()
    data class UnknownError(val message: String) : AppError()
}