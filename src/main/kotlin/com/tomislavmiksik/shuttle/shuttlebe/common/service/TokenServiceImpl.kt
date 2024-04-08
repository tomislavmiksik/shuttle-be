package com.tomislavmiksik.shuttle.shuttlebe.common.service

import com.tomislavmiksik.shuttle.shuttlebe.common.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenServiceImpl(private val jwtProperties: JwtProperties) : TokenService {

    private val secretKey = Keys.hmacShaKeyFor(jwtProperties.key.toByteArray())

    override fun generateAccessToken(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any>
    ): String =
        Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration))
            .add(additionalClaims)
            .and()
            .signWith(secretKey)
            .compact()

    override fun generateRefreshToken(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any>
    ): String {
        TODO("Not yet implemented")
    }

    override fun extractUsername(token: String): String? = getAllClaims(token).subject

    override fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    override fun isTokenExpired(token: String): Boolean {
        val expiration = getAllClaims(token).expiration
        return expiration.before(Date(System.currentTimeMillis()))
    }

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()
        return parser
            .parseSignedClaims(token)
            .payload
    }
}