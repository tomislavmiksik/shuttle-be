package com.tomislavmiksik.shuttle.shuttlebe.common.service

import com.tomislavmiksik.shuttle.shuttlebe.user.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import com.tomislavmiksik.shuttle.shuttlebe.user.entity.User as CustomUser

@Service
class CustomUserDetailsServiceImpl(val userRepository: UserRepository) : CustomUserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found")

        return user.map()
    }

    private fun CustomUser.map(): UserDetails {
        return User.builder()
            .username(username)
            .password(password)
            .roles(role.name)
            .build()
    }
}