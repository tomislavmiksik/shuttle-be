package com.tomislavmiksik.shuttle.shuttlebe.user.dto

data class UserLoginRequest(
    val username: String,
    val password: String
)