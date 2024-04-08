package com.tomislavmiksik.shuttle.shuttlebe.auth.controller

import com.tomislavmiksik.shuttle.shuttlebe.auth.dto.AuthLoginRequest
import com.tomislavmiksik.shuttle.shuttlebe.auth.dto.AuthRegisterRequest
import com.tomislavmiksik.shuttle.shuttlebe.auth.dto.AuthResponse
import com.tomislavmiksik.shuttle.shuttlebe.auth.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/v1/auth")
class AuthController(val authenticationService: AuthenticationService) {

    @PostMapping("/login")
    fun login(@RequestBody body: AuthLoginRequest): Any {
        val response = authenticationService.login(body)

        return ResponseEntity<AuthResponse>(response, HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(@RequestBody body: AuthRegisterRequest): Any {

        val registeredUser = authenticationService.register(body)

        return ResponseEntity<AuthResponse>(registeredUser, HttpStatus.CREATED)
    }

}
