package com.tomislavmiksik.shuttle.shuttlebe.events.dto

import com.tomislavmiksik.shuttle.shuttlebe.user.entity.User

data class EventResponse(
    val id: String,
    val name: String,
    val date: String,
    val owner: User,
)