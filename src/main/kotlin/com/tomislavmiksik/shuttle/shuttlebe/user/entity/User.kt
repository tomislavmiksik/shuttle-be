package com.tomislavmiksik.shuttle.shuttlebe.user.entity

import com.tomislavmiksik.shuttle.shuttlebe.user.dto.UserResponse
import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import java.util.*

@Entity
@Data
@Builder
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID,
    var email: String,
    var firstName: String,
    var lastName: String,
    var password: String,
    var token: String,
    var username: String,
    var role: Role,
) {
    fun toUserResponse(): UserResponse {
        return UserResponse(
            id = id.toString(),
            username = username,
            email = email,
            firstName = firstName,
            lastName = lastName,
            token = token,
        )
    }
}

enum class Role {
    ADMIN,
    USER
}