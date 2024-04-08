package com.tomislavmiksik.shuttle.shuttlebe.common.config

import com.tomislavmiksik.shuttle.shuttlebe.common.service.CustomUserDetailsService
import com.tomislavmiksik.shuttle.shuttlebe.common.service.CustomUserDetailsServiceImpl
import com.tomislavmiksik.shuttle.shuttlebe.user.repository.UserRepository
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class Configuration {

    @Bean
    fun userDetailsService(userRepository: UserRepository): CustomUserDetailsService =
        CustomUserDetailsServiceImpl(userRepository)

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(userRepository: UserRepository): AuthenticationProvider =
        DaoAuthenticationProvider().also {
            it.setUserDetailsService(userDetailsService(userRepository))
            it.setPasswordEncoder(passwordEncoder())
        }

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
        configuration.authenticationManager
}