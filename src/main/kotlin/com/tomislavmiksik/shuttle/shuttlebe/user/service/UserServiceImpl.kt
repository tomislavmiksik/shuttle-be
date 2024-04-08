package com.tomislavmiksik.shuttle.shuttlebe.user.service

import com.tomislavmiksik.shuttle.shuttlebe.user.mapper.UserDTOToUser
import com.tomislavmiksik.shuttle.shuttlebe.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val userDTOToUser: UserDTOToUser,
    val passwordEncoder: PasswordEncoder,
) : UserService {
//    override fun login(username: String, password: String): UserDTO? {
//        val user = userRepository.findByUsername(username) ?: return null
//        if (user.password != password) {
//            throw IncorrectCredentialsException()
//        }
//        return userToUserDTO.convert(user)
//    }
//
//    override fun register(
//        user: UserDTO
//    ): UserDTO {
//        if (userRepository.findByUsername(user.username) != null) {
//            throw UsernameTakenException()
//        }
//        if (userRepository.findByEmail(user.email) != null) {
//            throw EmailTakenException()
//        }
//
//        val updatedDto = UserDTO(
//            id = user.id,
//            username = user.username,
//            email = user.email,
//            firstName = user.firstName,
//            lastName = user.lastName,
//            password = passwordEncoder.encode(user.password),
//            token = user.token,
//            role = user.role,
//        )
//        return userRepository.save(userDTOToUser.convert(updatedDto)).let(userToUserDTO::convert)
//    }
}