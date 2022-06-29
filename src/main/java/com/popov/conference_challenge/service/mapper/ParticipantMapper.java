package com.popov.conference_challenge.service.mapper;

import com.popov.conference_challenge.repository.entity.Participant;
import com.popov.conference_challenge.service.dto.ParticipantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ParticipantMapper {
    ParticipantDto toDto(Participant entity);

    @Mapping(target = "active", defaultValue = "true")
    Participant toEntity(ParticipantDto entity);
}
