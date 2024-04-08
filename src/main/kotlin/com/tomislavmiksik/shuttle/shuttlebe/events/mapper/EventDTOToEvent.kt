package com.tomislavmiksik.shuttle.shuttlebe.events.mapper

import com.tomislavmiksik.shuttle.shuttlebe.common.config.MapperConfiguration
import com.tomislavmiksik.shuttle.shuttlebe.events.dto.EventDTO
import com.tomislavmiksik.shuttle.shuttlebe.events.entity.Event
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.core.convert.converter.Converter

@Mapper(config = MapperConfiguration::class)
interface EventDTOToEvent : Converter<EventDTO, Event> {
    @Mapping(target = "id", ignore = true)
    override fun convert(source: EventDTO): Event
}