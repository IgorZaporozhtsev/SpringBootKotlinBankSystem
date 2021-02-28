package com.zeecoder.ktutorials.exceptions

import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

data class ApiException(
        private val _errorMessage: String?,
        val error: HttpStatus = HttpStatus.NOT_FOUND,
        val exceptionCode: String = "GEEX000",
        val timestamp: ZonedDateTime = ZonedDateTime.now(),
) {
    val errorMessage: String
        get() = _errorMessage ?: "Something went wrong"

    val errorCode: Int
        get() = error.value()
}