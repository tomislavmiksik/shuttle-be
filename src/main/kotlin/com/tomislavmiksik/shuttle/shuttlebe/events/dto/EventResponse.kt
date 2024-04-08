package com.tomislavmiksik.shuttle.shuttlebe.events.dto

import com.tomislavmiksik.shuttle.shuttlebe.user.dto.UserDTO


data class EventResponse(
    val id: String,
    val name: String,
    val date: String,
    val owner: UserDTO,
)