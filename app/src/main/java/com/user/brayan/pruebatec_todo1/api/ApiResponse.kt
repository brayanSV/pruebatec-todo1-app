package com.user.brayan.pruebatec_todo1.api

import java.util.regex.Pattern

sealed class ApiResponse<T> {
    class ApiEmptyResponse<T>: ApiResponse<T>()
    class ApiSuccessResponse<T>(val body: T): ApiResponse<T>()
    class ApiErrorResponse<T>(val errorMessage: String): ApiResponse<T>()
}