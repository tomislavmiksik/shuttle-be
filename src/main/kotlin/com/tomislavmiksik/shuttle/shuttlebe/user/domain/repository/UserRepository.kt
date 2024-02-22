package com.tomislavmiksik.shuttle.shuttlebe.user.domain.repository

import com.tomislavmiksik.shuttle.shuttlebe.user.domain.entity.User
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : ListCrudRepository<User?, Long?> {
    fun findUserByUsername(username: String?): User?
    fun findStudentByUsernameAndPassword(username: String?, password: String?): User?
}