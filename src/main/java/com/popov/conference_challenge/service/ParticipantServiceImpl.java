package com.popov.conference_challenge.service;

import com.popov.conference_challenge.repository.ParticipantRepository;
import com.popov.conference_challenge.service.domain.Participant;
import com.popov.conference_challenge.service.dto.ParticipantDto;
import com.popov.conference_challenge.service.mapper.ParticipantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;
    public ParticipantServiceImpl(ParticipantRepository participantRepository,
                                  ParticipantMapper participantMapper) {
        this.participantRepository = participantRepository;
        this.participantMapper = participantMapper;
    }

    @Override
    @Transactional
    public ParticipantDto saveParticipant(ParticipantDto dto) {
        Participant result = participantRepository.save(participantMapper.toDomain(dto));
        return participantMapper.toDto(result);
    }

    @Override
    @Transactional
    public ParticipantDto deactivateParticipant(Long participantId) {
        Participant participant =
                participantRepository.findById(participantId);
        participant.deactivate();
        return participantMapper.toDto(participantRepository.save(participant));
    }
}
