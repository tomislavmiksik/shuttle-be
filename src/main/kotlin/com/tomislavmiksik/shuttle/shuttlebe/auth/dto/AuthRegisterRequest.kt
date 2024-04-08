package com.tomislavmiksik.shuttle.shuttlebe.auth.dto

import com.tomislavmiksik.shuttle.shuttlebe.user.dto.UserDTO
import com.tomislavmiksik.shuttle.shuttlebe.user.entity.Role
import java.util.*

data class AuthRegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
) {
    fun toUserFromRegisterRequest(): UserDTO {
        return UserDTO(
            id = UUID.randomUUID(),
            username = username,
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName,
            token = "",
            role = Role.USER
        )
    }
}