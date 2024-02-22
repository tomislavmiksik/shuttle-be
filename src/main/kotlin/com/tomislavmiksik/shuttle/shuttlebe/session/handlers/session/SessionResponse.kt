package com.tomislavmiksik.shuttle.shuttlebe.session.handlers.session

import java.util.*

data class SessionResponse(
    val id: UUID,
    val name: String,
    val date: Date,
)
