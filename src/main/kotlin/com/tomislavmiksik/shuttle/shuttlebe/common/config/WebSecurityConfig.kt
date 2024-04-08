package com.tomislavmiksik.shuttle.shuttlebe.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class WebSecurityConfig(private val authProvider: AuthenticationProvider) {
    @Bean
    fun filterChain(http: HttpSecurity, jwtAuthFilter: JwtAuthFilter): DefaultSecurityFilterChain {
        http {
            csrf {
                disable()
            }
            authorizeHttpRequests {
                authorize("/error", permitAll)
                authorize(HttpMethod.POST, "/api/v1/auth/**", permitAll)
                authorize(HttpMethod.POST, "/api/v1/user", authenticated)
                authorize("/api/v1/user/**", hasRole("ADMIN"))
                authorize(anyRequest, authenticated)
            }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            authProvider
            addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
        }
        return http.build()
    }
}