package com.tomislavmiksik.shuttle.shuttlebe.user.controller

import com.tomislavmiksik.shuttle.shuttlebe.exception.NotFoundException
import com.tomislavmiksik.shuttle.shuttlebe.user.dto.UserLoginRequest
import com.tomislavmiksik.shuttle.shuttlebe.user.dto.UserRegisterRequest
import com.tomislavmiksik.shuttle.shuttlebe.user.dto.UserResponse
import com.tomislavmiksik.shuttle.shuttlebe.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/v1/auth")
class UserController(val userService: UserService) {

    @PostMapping("/login")
    fun login(@RequestBody body: UserLoginRequest): Any {
        val user = userService.login(body.username, body.password) ?: throw NotFoundException()

        return ResponseEntity<UserResponse>(user.toUserResponse(), HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(@RequestBody body: UserRegisterRequest): Any {

        val registeredUser = userService.register(body.toUserFromRegisterRequest())

        return ResponseEntity<UserResponse>(registeredUser.toUserResponse(), HttpStatus.CREATED)
    }

}
