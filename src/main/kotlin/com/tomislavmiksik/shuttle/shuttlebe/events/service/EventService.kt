package com.tomislavmiksik.shuttle.shuttlebe.events.service

import com.tomislavmiksik.shuttle.shuttlebe.events.dto.EventDTO
import java.util.*

interface EventService {
    fun findAll(): List<EventDTO>
    fun findById(id: UUID): EventDTO
}