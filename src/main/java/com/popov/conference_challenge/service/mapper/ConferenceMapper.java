package com.popov.conference_challenge.service.mapper;

import com.popov.conference_challenge.repository.entity.Conference;
import com.popov.conference_challenge.service.dto.ConferenceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConferenceMapper {
    ConferenceDto toDto(Conference entity);

    @Mapping(target = "cancelled", defaultValue = "false")
    Conference toEntity(ConferenceDto entity);
}
