package com.zeecoder.ktutorials.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(ApiBankException::class)
    fun handleNotFound(exception: ApiBankException): ResponseEntity<ApiException> =
        ResponseEntity(ApiException(exception.message), HttpStatus.NOT_FOUND)
}