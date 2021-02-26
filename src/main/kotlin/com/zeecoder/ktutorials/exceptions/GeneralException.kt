package com.zeecoder.ktutorials.exceptions

import org.springframework.http.HttpStatus
import java.time.ZoneId
import java.time.ZonedDateTime

enum class GeneralException
(
        val message: String,
        val httpStatus: HttpStatus,
        val exceptionCode: String,
        val timestamp: ZonedDateTime
) {
    USER_NOT_FOUND("User is not present", HttpStatus.NOT_FOUND, "GEEX001", ZonedDateTime.now(ZoneId.of("Z"))),
    USER_ALREADY_EXIST("User already exist", HttpStatus.BAD_REQUEST, "GEEX002", ZonedDateTime.now(ZoneId.of("Z")))
}