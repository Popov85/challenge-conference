package com.popov.conference_challenge.service.mapper;

import com.popov.conference_challenge.repository.entity.ConferenceEntity;
import com.popov.conference_challenge.service.domain.Conference;
import com.popov.conference_challenge.service.dto.ConferenceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ReferenceMapper.class})
public interface ConferenceMapper {
    ConferenceDto toDto(ConferenceEntity entity);

    ConferenceDto toDto(Conference entity);

    Conference toDomain(ConferenceEntity entity);

    Conference toDomain(ConferenceDto entity);

    @Mapping(target = "cancelled", defaultValue = "false")
    ConferenceEntity toEntity(ConferenceDto entity);

    @Mapping(target = "cancelled", defaultValue = "false")
    ConferenceEntity toEntity(Conference entity);
    ConferenceEntity toEntity(Long conferenceId);
}
