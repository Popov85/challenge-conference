package com.popov.conference_challenge.repository.impl;

import com.popov.conference_challenge.repository.ConferenceParticipantRepository;
import com.popov.conference_challenge.repository.entity.ConferenceParticipantEntity;
import com.popov.conference_challenge.repository.entity.ConferenceParticipantEntityId;
import com.popov.conference_challenge.repository.infrastructure.SpringDataConferenceParticipantRepository;
import com.popov.conference_challenge.service.domain.Conference;
import com.popov.conference_challenge.service.domain.ConferenceParticipant;
import com.popov.conference_challenge.service.mapper.ConferenceMapper;
import com.popov.conference_challenge.service.mapper.ConferenceParticipantMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ConferenceParticipantRepositoryImpl implements ConferenceParticipantRepository {

    private final ConferenceMapper conferenceMapper;

    private final ConferenceParticipantMapper conferenceParticipantMapper;

    private final SpringDataConferenceParticipantRepository springDataConferenceParticipantRepository;

    public ConferenceParticipantRepositoryImpl(ConferenceMapper conferenceMapper,
                                               ConferenceParticipantMapper conferenceParticipantMapper,
                                               SpringDataConferenceParticipantRepository springDataConferenceParticipantRepository) {
        this.conferenceMapper = conferenceMapper;
        this.conferenceParticipantMapper = conferenceParticipantMapper;
        this.springDataConferenceParticipantRepository = springDataConferenceParticipantRepository;
    }

    @Override
    public Long countByConference(Conference conference) {
        return springDataConferenceParticipantRepository
                .countByConference(conferenceMapper.toEntity(conference));
    }

    @Override
    public ConferenceParticipant save(ConferenceParticipant conferenceParticipant) {
        return conferenceParticipantMapper.toDomain(springDataConferenceParticipantRepository
                .save(conferenceParticipantMapper.toEntity(conferenceParticipant)));
    }

    @Override
    public Page<ConferenceParticipant> findByConference(Long conferenceId, Pageable pageable) {
        Page<ConferenceParticipantEntity> page = springDataConferenceParticipantRepository
                .findByConference(conferenceMapper.toEntity(conferenceId), pageable);
        return page.map(e->conferenceParticipantMapper.toDomain(e));
    }

    @Override
    public void deleteById(ConferenceParticipant conferenceParticipant) {
        springDataConferenceParticipantRepository.deleteById(
                new ConferenceParticipantEntityId(conferenceParticipant.getConferenceId(), conferenceParticipant.getConferenceId()));
    }
}
