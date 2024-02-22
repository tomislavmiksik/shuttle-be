package com.tomislavmiksik.shuttle.shuttlebe.auth.domain.service

import org.springframework.security.core.userdetails.UserDetails
import java.util.*


interface JwtGeneratorService {
    fun extractUsername(token: String?): String?

    fun extractExpiration(token: String?): Date?

    fun generateToken(username: String?): String?

    fun validateToken(token: String?, userDetails: UserDetails?): Boolean?
}