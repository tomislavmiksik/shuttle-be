package com.tomislavmiksik.shuttle.shuttlebe.auth.domain.service

import lombok.AllArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@AllArgsConstructor
class UserDetailsImpl(
    private val username: String? = null,
    private val password: String? = null,
    private val authorities: List<GrantedAuthority>? = null
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority>? {
        return this.authorities
    }

    override fun getPassword(): String? {
        return this.password
    }

    override fun getUsername(): String? {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}