package com.tomislavmiksik.shuttle.shuttlebe.session.handlers

import com.tomislavmiksik.shuttle.shuttlebe.session.domain.entity.Session
import com.tomislavmiksik.shuttle.shuttlebe.session.domain.repository.SessionRepository
import com.tomislavmiksik.shuttle.shuttlebe.session.handlers.session.SessionResponse
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/api/session")
class SessionHandler(val repository: SessionRepository) {

    @GetMapping
    fun sessions(
        @RequestParam(defaultValue = "20") limit: Int,
        @RequestParam(defaultValue = "0") offset: Int,
        @RequestParam(defaultValue = "") tag: String,
    ): List<SessionResponse> {
        val page = PageRequest.of(offset, limit, Sort.Direction.DESC, "createdAt")

        val sessions = repository.findAll()

        return sessions.map { it.toResponse() }
    }

    private fun Session.toResponse(): SessionResponse = SessionResponse(
        id = this.id,
        name = this.name,
        date = this.date
    )
}