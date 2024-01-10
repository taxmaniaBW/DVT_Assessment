package com.utick.dvtcodingassessment.util

sealed class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : ApiResponse<T>(data = data)

    class Error<T>(errorMessage: String) : ApiResponse<T>(message = errorMessage)

    class Loading<T> : ApiResponse<T>()
}