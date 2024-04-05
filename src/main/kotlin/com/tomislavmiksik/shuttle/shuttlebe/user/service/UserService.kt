package com.tomislavmiksik.shuttle.shuttlebe.user.service

import com.tomislavmiksik.shuttle.shuttlebe.user.entity.User


interface UserService {

    fun login(username: String, password: String): User?

    fun register(user: User): User
}