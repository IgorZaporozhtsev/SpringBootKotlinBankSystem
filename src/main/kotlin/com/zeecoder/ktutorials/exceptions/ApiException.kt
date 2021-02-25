package com.zeecoder.ktutorials.exceptions

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ApiException(
        private val _errorMessage: String?,
        val error: HttpStatus = HttpStatus.NOT_FOUND,
        val timestamp: LocalDateTime = LocalDateTime.now(),
) {
    val errorMessage: String
        get() = _errorMessage ?: "Something went wrong"

    val errorCode: Int
        get() = error.value()
}