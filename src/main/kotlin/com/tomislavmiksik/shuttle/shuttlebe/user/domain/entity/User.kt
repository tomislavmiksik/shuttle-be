package com.tomislavmiksik.shuttle.shuttlebe.user.domain.entity

import com.fasterxml.jackson.annotation.JsonRootName
import com.tomislavmiksik.shuttle.shuttlebe.session.domain.entity.Session
import jakarta.persistence.*
import java.util.*

@Entity
@JsonRootName("users")
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = "",
    var email: String = "",
    var username: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var password: String = "",
    var token: String = "",
    var createdAt: Date = Date(),
    var lastUpdated: Date = Date(),
    @OneToMany
    var sessions: List<Session> = ArrayList()
)
