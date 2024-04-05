package com.tomislavmiksik.shuttle.shuttlebe.events.entity

import com.tomislavmiksik.shuttle.shuttlebe.user.entity.User
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.util.*

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "events")
class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID,
    var name: String,
    var date: Date,
    @ManyToOne var owner: User,
    var createdAt: Date,
    var updatedAt: Date,
)