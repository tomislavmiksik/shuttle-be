package com.tomislavmiksik.shuttle.shuttlebe.common.service

import com.tomislavmiksik.shuttle.shuttlebe.common.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.bouncycastle.util.io.pem.PemReader
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.io.FileReader
import java.io.StringReader
import java.security.KeyFactory
import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*

@Service
class TokenServiceImpl(private val jwtProperties: JwtProperties) : TokenService {


    private lateinit var keyPair: KeyPair

    init {
        val publicKey = getKeyFromPem(FileReader("public.pem").readText())
        val privateKey = getPrivateKeyFromPem(FileReader("private.pem").readText())
        keyPair = KeyPair(publicKey, privateKey)
    }

    private final fun getKeyFromPem(pemEncodedPublicKey: String): PublicKey {
        val reader = PemReader(StringReader(pemEncodedPublicKey))
        val pemObject = reader.readPemObject()
        val keySpec = X509EncodedKeySpec(pemObject.content)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePublic(keySpec)
    }

    private final fun getPrivateKeyFromPem(privateKeyPem: String): PrivateKey {
        val pemReader = PemReader(StringReader(privateKeyPem))
        val pemObject = pemReader.readPemObject()
        val keyBytes = pemObject.content
        val keyFactory = KeyFactory.getInstance("RSA")
        val keySpec = PKCS8EncodedKeySpec(keyBytes)
        val privateKey = keyFactory.generatePrivate(keySpec)
        return privateKey
    }

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
            .signWith(keyPair.private)
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
        val valid = username == userDetails.username && !isTokenExpired(token)

        return valid
    }

    override fun isTokenExpired(token: String): Boolean {
        val expiration = getAllClaims(token).expiration
        return expiration.before(Date(System.currentTimeMillis()))
    }

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(keyPair.public)
            .build()
        val claims = parser
            .parseSignedClaims(token)
            .payload

        return claims
    }
}