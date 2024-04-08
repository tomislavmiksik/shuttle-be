package com.tomislavmiksik.shuttle.shuttlebe.common.config

import com.tomislavmiksik.shuttle.shuttlebe.common.service.CustomUserDetailsService
import com.tomislavmiksik.shuttle.shuttlebe.common.service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Order(1)
class JwtAuthFilter(
    val tokenService: TokenService,
    val userDetailsService: CustomUserDetailsService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")


        if (authHeader.doesNotContainBearerToken()) {
            filterChain.doFilter(request, response)
            return
        }


        val token = authHeader.extractTokenValue()
        val username = tokenService.extractUsername(token)


        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val foundUser = userDetailsService.loadUserByUsername(username)
            if (tokenService.isTokenValid(token, foundUser)) {
                updateContext(foundUser, request)
            }
            filterChain.doFilter(request, response)
        }
    }


    private fun String?.doesNotContainBearerToken() =
        this == null || !this.startsWith("Bearer ")

    private fun String.extractTokenValue() =
        this.substringAfter("Bearer ")

    private fun updateContext(foundUser: UserDetails, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(foundUser, null, foundUser.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken
    }
}