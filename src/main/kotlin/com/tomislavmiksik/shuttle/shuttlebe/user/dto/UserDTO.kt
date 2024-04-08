package com.tomislavmiksik.shuttle.shuttlebe.user.dto

import com.tomislavmiksik.shuttle.shuttlebe.auth.dto.UserRegistrationResponse
import com.tomislavmiksik.shuttle.shuttlebe.user.entity.Role
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.util.*

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UserDTO(
    val id: UUID,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val token: String,
    val role: Role
) {
    fun toUserResponse(): UserRegistrationResponse {
        return UserRegistrationResponse(
            id = id.toString(),
            username = username,
            email = email,
            firstName = firstName,
            lastName = lastName,
            token = token,
        )
    }
}