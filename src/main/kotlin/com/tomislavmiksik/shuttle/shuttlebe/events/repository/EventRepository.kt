package com.tomislavmiksik.shuttle.shuttlebe.events.repository

import com.tomislavmiksik.shuttle.shuttlebe.events.entity.Event
import org.springframework.data.repository.CrudRepository
import java.util.*

interface EventRepository : CrudRepository<Event, UUID> {
    fun findAllByOrderByCreatedAtDesc(): List<Event>
}