package com.tomislavmiksik.shuttle.shuttlebe.user.dto

import com.tomislavmiksik.shuttle.shuttlebe.user.entity.Role
import com.tomislavmiksik.shuttle.shuttlebe.user.entity.User
import java.util.*

data class UserRegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
) {
    fun toUserFromRegisterRequest(): User {
        return User(
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