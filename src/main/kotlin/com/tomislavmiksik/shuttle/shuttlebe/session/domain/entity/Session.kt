package com.tomislavmiksik.shuttle.shuttlebe.session.domain.entity

import com.fasterxml.jackson.annotation.JsonRootName
import com.tomislavmiksik.shuttle.shuttlebe.user.domain.entity.User
import jakarta.persistence.*
import java.util.*

@Entity
@JsonRootName("sessions")
@Table(name = "sessions")
data class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID.randomUUID(),
    var name: String = "",
    var date: Date = Date(),
    @ManyToOne
    var owner: User = User(),
    var createdAt: Date = Date(),
    var lastUpdated: Date = Date(),
)
