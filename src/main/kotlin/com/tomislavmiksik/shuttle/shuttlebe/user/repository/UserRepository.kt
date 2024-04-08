package com.tomislavmiksik.shuttle.shuttlebe.user.repository

import com.tomislavmiksik.shuttle.shuttlebe.user.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID> {
    fun findByUsername(username: String?): User?
    fun findByEmail(email: String?): User?
    fun save(user: User?): User
}