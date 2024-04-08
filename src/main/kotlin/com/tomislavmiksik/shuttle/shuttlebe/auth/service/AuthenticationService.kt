package com.tomislavmiksik.shuttle.shuttlebe.auth.service

import com.tomislavmiksik.shuttle.shuttlebe.auth.dto.AuthLoginRequest
import com.tomislavmiksik.shuttle.shuttlebe.auth.dto.AuthRegisterRequest
import com.tomislavmiksik.shuttle.shuttlebe.auth.dto.AuthResponse

interface AuthenticationService {
    fun login(authLoginRequest: AuthLoginRequest): AuthResponse
    fun register(authRegisterRequest: AuthRegisterRequest): AuthResponse
}