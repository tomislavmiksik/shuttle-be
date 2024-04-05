package com.tomislavmiksik.shuttle.shuttlebe.user.dto

data class UserResponse(
    val id: String,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val token: String,
)