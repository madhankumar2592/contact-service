package com.beo.infra.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleDeserializationError(ex: HttpMessageNotReadableException): ResponseEntity<Map<String, String>> {
        val rootCause = ex.rootCause
        if (rootCause is IllegalArgumentException) {
            return ResponseEntity
                .badRequest()
                .body(mapOf("error" to rootCause.message.toString()))
        }

        return ResponseEntity
            .badRequest()
            .body(mapOf("error" to "Missing or invalid fields in request JSON"))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleDomainValidation(ex: IllegalArgumentException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .badRequest()
            .body(mapOf("error" to ex.message.toString()))
    }
}