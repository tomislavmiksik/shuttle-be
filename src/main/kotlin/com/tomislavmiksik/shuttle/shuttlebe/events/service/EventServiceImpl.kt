package com.tomislavmiksik.shuttle.shuttlebe.events.service

import com.tomislavmiksik.shuttle.shuttlebe.events.dto.EventDTO
import com.tomislavmiksik.shuttle.shuttlebe.events.mapper.EventToEventDTO
import com.tomislavmiksik.shuttle.shuttlebe.events.repository.EventRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventServiceImpl
    (
    val eventRepository: EventRepository,
    val eventToEventDTO: EventToEventDTO,
) : EventService {
    override fun findAll(): List<EventDTO> =
        eventRepository.findAllByOrderByCreatedAtDesc().map(eventToEventDTO::convert)

    override fun findById(id: UUID): EventDTO = eventToEventDTO.convert(eventRepository.findById(id).get())
}