package com.zeecoder.ktutorials.util

import com.zeecoder.ktutorials.exceptions.BankException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAppHandler {

    @ExceptionHandler(BankException::class)
    fun handleNotFound(exception: BankException): ResponseEntity<ApiError> =
        ResponseEntity(ApiError(exception.message), HttpStatus.NOT_FOUND)

}