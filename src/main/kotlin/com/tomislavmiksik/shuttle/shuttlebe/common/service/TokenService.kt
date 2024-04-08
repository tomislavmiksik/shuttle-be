package com.tomislavmiksik.shuttle.shuttlebe.common.service

import org.springframework.security.core.userdetails.UserDetails
import java.util.*


interface TokenService {
    fun generateAccessToken(userDetails: UserDetails, expirationDate: Date, additionalClaims: Map<String, Any>): String

    fun generateRefreshToken(userDetails: UserDetails, expirationDate: Date, additionalClaims: Map<String, Any>): String

    fun extractUsername(token: String): String?

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean

    fun isTokenExpired(token: String): Boolean
}