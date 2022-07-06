package com.popov.conference_challenge.repository.impl;

import com.popov.conference_challenge.repository.ConferenceRepository;
import com.popov.conference_challenge.repository.infrastructure.SpringDataConferenceRepository;
import com.popov.conference_challenge.service.domain.Conference;
import com.popov.conference_challenge.service.mapper.ConferenceMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public class ConferenceRepositoryImpl implements ConferenceRepository {

    private final SpringDataConferenceRepository springDataConferenceRepository;

    private final ConferenceMapper conferenceMapper;

    public ConferenceRepositoryImpl(SpringDataConferenceRepository springDataConferenceRepository, ConferenceMapper conferenceMapper) {
        this.springDataConferenceRepository = springDataConferenceRepository;
        this.conferenceMapper = conferenceMapper;
    }

    @Override
    public Conference findById(Long id) {
        return conferenceMapper.toDomain(springDataConferenceRepository
                .findById(id).orElseThrow(()->new EntityNotFoundException("Conference not found with id="+id)));
    }

    @Override
    public Conference save(Conference conference) {
        return conferenceMapper
                .toDomain(springDataConferenceRepository
                .save(conferenceMapper.toEntity(conference)));
    }
}
