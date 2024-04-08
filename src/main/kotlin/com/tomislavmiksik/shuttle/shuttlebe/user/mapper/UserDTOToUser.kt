package com.tomislavmiksik.shuttle.shuttlebe.user.mapper

import com.tomislavmiksik.shuttle.shuttlebe.common.config.MapperConfiguration
import com.tomislavmiksik.shuttle.shuttlebe.user.dto.UserDTO
import com.tomislavmiksik.shuttle.shuttlebe.user.entity.User
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(config = MapperConfiguration::class)
interface UserDTOToUser : Converter<UserDTO, User> {
    override fun convert(source: UserDTO): User
}