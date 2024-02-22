package com.tomislavmiksik.shuttle.shuttlebe.auth

import UserDetailsServiceImpl
import com.tomislavmiksik.shuttle.shuttlebe.auth.domain.service.JwtGeneratorService
import io.jsonwebtoken.io.IOException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.AllArgsConstructor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
@AllArgsConstructor
class JwtAuthFilter : OncePerRequestFilter() {
    private val jwtGeneratorService: JwtGeneratorService? = null
    private val userDetailsService: UserDetailsServiceImpl? = null

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        var token: String? = null
        var username: String? = null

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7)
            username = jwtGeneratorService?.extractUsername(token)
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails: UserDetails? = userDetailsService?.loadUserByUsername(username)

            if (jwtGeneratorService?.validateToken(token, userDetails) == true) {
                val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails?.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filterChain.doFilter(request, response)
    }
}