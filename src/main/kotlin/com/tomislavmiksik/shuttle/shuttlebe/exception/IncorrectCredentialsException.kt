package com.tomislavmiksik.shuttle.shuttlebe.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class IncorrectCredentialsException : RuntimeException()
