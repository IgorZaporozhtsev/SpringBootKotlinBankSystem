package com.zeecoder.ktutorials.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(ApiBankException::class)
    fun handleNotFound(exception: ApiBankException): ResponseEntity<ApiException> {

        //todo what does !! mean

        val status = GeneralException.values().first { it.exceptionCode == exception.exceptionCode }
        val exceptionInfo = ApiException("${status.message}. ${exception.message}", status.httpStatus, status.exceptionCode, status.timestamp )

        return ResponseEntity(exceptionInfo, status.httpStatus)
    }
}

