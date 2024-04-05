package com.tomislavmiksik.shuttle.shuttlebe.events.controller

import com.tomislavmiksik.shuttle.shuttlebe.events.dto.EventDTO
import com.tomislavmiksik.shuttle.shuttlebe.events.dto.EventResponse
import com.tomislavmiksik.shuttle.shuttlebe.events.service.EventService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/events")
class EventController(
    private val eventService: EventService
) {

    @GetMapping()
    fun findAll(): List<EventResponse> {
        val events = eventService.findAll()
        if (eventService.findAll().isEmpty()) {
            return emptyList()
        }
        return events.map { toResponse(it) }
    }


    fun toResponse(eventDTO: EventDTO): EventResponse {
        return EventResponse(
            id = eventDTO.id,
            name = eventDTO.name,
            date = eventDTO.date.toString(),
            owner = eventDTO.owner
        )
    }
}