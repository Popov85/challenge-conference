package com.popov.conference_challenge.service.mapper;

import com.popov.conference_challenge.repository.entity.ConferenceEntity;
import com.popov.conference_challenge.repository.entity.ParticipantEntity;
import com.popov.conference_challenge.service.domain.Participant;
import com.popov.conference_challenge.service.dto.ParticipantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ReferenceMapper.class})
public interface ParticipantMapper {
    ParticipantDto toDto(ParticipantEntity entity);

    ParticipantDto toDto(Participant domain);

    Participant toDomain(ParticipantEntity entity);

    Participant toDomain(ParticipantDto dto);

    @Mapping(target = "active", defaultValue = "true")
    ParticipantEntity toEntity(ParticipantDto entity);

    @Mapping(target = "active", defaultValue = "true")
    ParticipantEntity toEntity(Participant entity);
    ParticipantEntity toEntity(Long participantId);
}
