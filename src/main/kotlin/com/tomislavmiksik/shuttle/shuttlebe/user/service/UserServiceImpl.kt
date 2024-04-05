package com.tomislavmiksik.shuttle.shuttlebe.user.service

import com.tomislavmiksik.shuttle.shuttlebe.exception.EmailTakenException
import com.tomislavmiksik.shuttle.shuttlebe.exception.IncorrectCredentialsException
import com.tomislavmiksik.shuttle.shuttlebe.exception.UsernameTakenException
import com.tomislavmiksik.shuttle.shuttlebe.user.entity.Role
import com.tomislavmiksik.shuttle.shuttlebe.user.entity.User
import com.tomislavmiksik.shuttle.shuttlebe.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository
) : UserService {
    override fun login(username: String, password: String): User? {
        val user = userRepository.findByUsername(username) ?: return null
        if (user.password != password) {
            throw IncorrectCredentialsException()
        }
        return user
    }

    override fun register(
        user: User
    ): User {
        if (userRepository.findByUsername(user.username) != null) {
            throw UsernameTakenException()
        }
        if (userRepository.findByEmail(user.email) != null) {
            throw EmailTakenException()
        }
        user.role = Role.USER
        return userRepository.save(user)
    }
}