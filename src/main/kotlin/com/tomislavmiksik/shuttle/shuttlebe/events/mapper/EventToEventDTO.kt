package com.tomislavmiksik.shuttle.shuttlebe.events.mapper

import com.tomislavmiksik.shuttle.shuttlebe.common.config.MapperConfiguration
import com.tomislavmiksik.shuttle.shuttlebe.events.dto.EventDTO
import com.tomislavmiksik.shuttle.shuttlebe.events.entity.Event
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(config = MapperConfiguration::class)
interface EventToEventDTO : Converter<Event, EventDTO> {
    override fun convert(source: Event): EventDTO
}