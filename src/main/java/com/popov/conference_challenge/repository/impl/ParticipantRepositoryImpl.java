package com.popov.conference_challenge.repository.impl;

import com.popov.conference_challenge.repository.ParticipantRepository;
import com.popov.conference_challenge.repository.infrastructure.SpringDataParticipantRepository;
import com.popov.conference_challenge.service.domain.Participant;
import com.popov.conference_challenge.service.mapper.ParticipantMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public class ParticipantRepositoryImpl implements ParticipantRepository {

    private final ParticipantMapper participantMapper;

    private final SpringDataParticipantRepository springDataParticipantRepository;

    public ParticipantRepositoryImpl(ParticipantMapper participantMapper,
                                     SpringDataParticipantRepository springDataParticipantRepository) {
        this.participantMapper = participantMapper;
        this.springDataParticipantRepository = springDataParticipantRepository;
    }

    @Override
    public Participant findById(Long id) {
        return participantMapper.toDomain(springDataParticipantRepository
                .findById(id).orElseThrow(()->new EntityNotFoundException("Conference not found with id="+id)));
    }

    @Override
    public Participant save(Participant participant) {
        return participantMapper
                .toDomain(springDataParticipantRepository
                        .save(participantMapper.toEntity(participant)));
    }
}
