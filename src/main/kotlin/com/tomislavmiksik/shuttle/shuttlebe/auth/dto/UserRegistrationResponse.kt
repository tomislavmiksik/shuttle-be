package com.tomislavmiksik.shuttle.shuttlebe.auth.dto

data class UserRegistrationResponse(
    val id: String?,
    val username: String?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val token: String?,
)