package com.popov.conference_challenge.service.mapper;

import com.popov.conference_challenge.repository.entity.ConferenceParticipantEntity;
import com.popov.conference_challenge.service.domain.ConferenceParticipant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ConferenceMapper.class, ParticipantMapper.class})
public interface ConferenceParticipantMapper {

    @Mapping(target = "conference", source = "conferenceId")
    @Mapping(target = "participant", source = "participantId")
    ConferenceParticipantEntity toEntity(ConferenceParticipant domain);

    @Mapping(target = "conferenceId", source = "conference.id")
    @Mapping(target = "participantId", source = "participant.id")
    ConferenceParticipant toDomain(ConferenceParticipantEntity entity);

}
