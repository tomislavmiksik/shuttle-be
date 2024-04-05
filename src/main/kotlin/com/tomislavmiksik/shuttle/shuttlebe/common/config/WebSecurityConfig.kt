package com.tomislavmiksik.shuttle.shuttlebe.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf {
                disable()
            }
            authorizeHttpRequests {
                authorize("/api/v1/auth/**", permitAll)
                authorize("/api/v1/events/**", authenticated)
            }
            authenticationManager = AuthenticationManager { authentication ->
                authentication
            }
            httpBasic { }
            formLogin { }
        }
        return http.build()
    }
}