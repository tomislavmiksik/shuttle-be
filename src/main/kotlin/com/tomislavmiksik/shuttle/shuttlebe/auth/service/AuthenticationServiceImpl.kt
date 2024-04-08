package com.tomislavmiksik.shuttle.shuttlebe.auth.service

import com.tomislavmiksik.shuttle.shuttlebe.auth.dto.AuthLoginRequest
import com.tomislavmiksik.shuttle.shuttlebe.auth.dto.AuthRegisterRequest
import com.tomislavmiksik.shuttle.shuttlebe.auth.dto.AuthResponse
import com.tomislavmiksik.shuttle.shuttlebe.common.config.JwtProperties
import com.tomislavmiksik.shuttle.shuttlebe.common.service.CustomUserDetailsService
import com.tomislavmiksik.shuttle.shuttlebe.common.service.TokenService
import com.tomislavmiksik.shuttle.shuttlebe.exception.EmailTakenException
import com.tomislavmiksik.shuttle.shuttlebe.exception.UsernameTakenException
import com.tomislavmiksik.shuttle.shuttlebe.user.dto.UserDTO
import com.tomislavmiksik.shuttle.shuttlebe.user.entity.Role
import com.tomislavmiksik.shuttle.shuttlebe.user.mapper.UserDTOToUser
import com.tomislavmiksik.shuttle.shuttlebe.user.mapper.UserToUserDTO
import com.tomislavmiksik.shuttle.shuttlebe.user.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationServiceImpl(
    val authenticationManager: AuthenticationManager,
    val userDetailsService: CustomUserDetailsService,
    val tokenService: TokenService,
    val jwtProperties: JwtProperties,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
    val userDTOToUser: UserDTOToUser,
    val userToUserDTO: UserToUserDTO,
) : AuthenticationService {
    override fun login(authLoginRequest: AuthLoginRequest): AuthResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(authLoginRequest.username, authLoginRequest.password)
        )

        val user = userDetailsService.loadUserByUsername(authLoginRequest.username)

        val token = createAccessToken(user)

        return AuthResponse(token)
    }

    private fun createAccessToken(user: UserDetails) = tokenService.generateAccessToken(
        userDetails = user,
        expirationDate = getAccessTokenExpiration(),
        additionalClaims = emptyMap()
    )

    private fun getAccessTokenExpiration(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)

    override fun register(
        authRegisterRequest: AuthRegisterRequest
    ): AuthResponse {
        if (userRepository.findByUsername(authRegisterRequest.username) != null) {
            throw UsernameTakenException()
        }
        if (userRepository.findByEmail(authRegisterRequest.email) != null) {
            throw EmailTakenException()
        }

        val userDto = UserDTO(
            id = UUID.randomUUID(),
            username = authRegisterRequest.username,
            email = authRegisterRequest.email,
            firstName = authRegisterRequest.firstName,
            lastName = authRegisterRequest.lastName,
            password = passwordEncoder.encode(authRegisterRequest.password),
            token = "",
            role = Role.USER,
        )

        val user = userRepository.save(userDTOToUser.convert(userDto))

        val userDetails = userDetailsService.loadUserByUsername(user.username)
        val token = createAccessToken(userDetails)
        return AuthResponse(token)
//        return userRepository.save(userDTOToUser.convert(updatedDto)).let { user ->
//            val userDetails = userDetailsService.loadUserByUsername(user.username)
//            val token = createAccessToken(userDetails)
//            AuthResponse(token)
//        }
    }
}