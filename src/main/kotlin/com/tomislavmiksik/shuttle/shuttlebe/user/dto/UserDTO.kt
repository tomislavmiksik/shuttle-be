package com.tomislavmiksik.shuttle.shuttlebe.user.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UserDTO(
    val id: String,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val token: String,
)