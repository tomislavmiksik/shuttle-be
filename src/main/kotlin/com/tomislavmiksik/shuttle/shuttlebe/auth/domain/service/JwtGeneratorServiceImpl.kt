package com.tomislavmiksik.shuttle.shuttlebe.auth.domain.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtGeneratorServiceImpl : JwtGeneratorService {
    private val secret = "357638792F423F4528482B4D6251655468576D5A7133743677397A2443264629"

    override fun extractUsername(token: String?): String? {
        return extractClaim(token, Claims::getSubject)
    }

    override fun extractExpiration(token: String?): Date? {
        return extractClaim(token, Claims::getExpiration)
    }

    override fun generateToken(username: String?): String {
        val claims: Map<String, Any?> = HashMap()
        return createToken(claims, username)
    }

    override fun validateToken(token: String?, userDetails: UserDetails?): Boolean {
        val username = extractUsername(token)
        return (username == userDetails?.username && !isTokenExpired(token))
    }

    private fun <T> extractClaim(token: String?, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String?): Claims {
        return Jwts
            .parser()
            .build()
            .parseSignedClaims(token)
            .payload
    }

    private fun createToken(claims: Map<String, Any?>, username: String?): String {
        return Jwts.builder()
            .claims(claims)
            .subject(username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 45)) //45 minutes
            .signWith(getSignKey(), Jwts.SIG.HS512)
            .compact()
    }

    private fun getSignKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(secret)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    private fun isTokenExpired(token: String?): Boolean {
        return extractExpiration(token)?.before(Date()) == true
    }
}