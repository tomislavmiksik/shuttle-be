package com.tomislavmiksik.shuttle.shuttlebe.events.dto

import com.tomislavmiksik.shuttle.shuttlebe.user.entity.User
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.util.*

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class EventDTO(
    var id: String,
    var name: String,
    var date: Date,
    var owner: User,
    var createdAt: Date,
    var updatedAt: Date,
)