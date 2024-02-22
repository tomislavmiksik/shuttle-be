package com.tomislavmiksik.shuttle.shuttlebe.session.domain.repository

import com.tomislavmiksik.shuttle.shuttlebe.session.domain.entity.Session
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SessionRepository : CrudRepository<Session, UUID>