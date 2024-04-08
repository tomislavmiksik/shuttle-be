package com.tomislavmiksik.shuttle.shuttlebe.common.controller

import com.tomislavmiksik.shuttle.shuttlebe.common.exception.EmailTakenException
import com.tomislavmiksik.shuttle.shuttlebe.common.exception.IncorrectCredentialsException
import com.tomislavmiksik.shuttle.shuttlebe.common.exception.NotFoundException
import com.tomislavmiksik.shuttle.shuttlebe.common.exception.UsernameTakenException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomExceptionController {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(): ResponseEntity<Any> {
        return ResponseEntity.notFound().build()
    }

    @ExceptionHandler(IncorrectCredentialsException::class)
    fun handleIncorrectCredentialsException(): ResponseEntity<Any> {
        return ResponseEntity.status(401).build()
    }

    @ExceptionHandler(UsernameTakenException::class)
    fun handleUsernameTakenException(): ResponseEntity<Any> {
        return ResponseEntity.badRequest().build()
    }

    @ExceptionHandler(EmailTakenException::class)
    fun handleEmailTakenException(): ResponseEntity<Any> {
        return ResponseEntity.badRequest().build()
    }


}