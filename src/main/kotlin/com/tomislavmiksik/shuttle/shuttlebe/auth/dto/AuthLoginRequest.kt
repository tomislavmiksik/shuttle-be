package com.tomislavmiksik.shuttle.shuttlebe.auth.dto

data class AuthLoginRequest(
    val username: String,
    val password: String
)