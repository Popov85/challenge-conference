package com.popov.conference_challenge.service;

import com.popov.conference_challenge.repository.ConferenceParticipantRepository;
import com.popov.conference_challenge.repository.ConferenceRepository;
import com.popov.conference_challenge.repository.entity.Conference;
import com.popov.conference_challenge.repository.entity.ConferenceParticipant;
import com.popov.conference_challenge.repository.entity.ConferenceParticipantId;
import com.popov.conference_challenge.repository.entity.Participant;
import com.popov.conference_challenge.service.dto.ParticipantDto;
import com.popov.conference_challenge.service.mapper.ParticipantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Service
public class ConferenceParticipantServiceImpl implements ConferenceParticipantService {

    @PersistenceContext
    private final EntityManager entityManager;

    private final ParticipantMapper participantMapper;

    private final ConferenceRepository conferenceRepository;

    private final ConferenceParticipantRepository conferenceParticipantRepository;

    public ConferenceParticipantServiceImpl(EntityManager entityManager,
                                            ParticipantMapper participantMapper,
                                            ConferenceRepository conferenceRepository,
                                            ConferenceParticipantRepository conferenceParticipantRepository) {
        this.entityManager = entityManager;
        this.participantMapper = participantMapper;
        this.conferenceRepository=conferenceRepository;
        this.conferenceParticipantRepository = conferenceParticipantRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer checkAvailability(Long conferenceId) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found, conferenceId = " + conferenceId));
        var reservedSeats = conference.getSeats();
        var alreadyBookedSeats = conferenceParticipantRepository.countByConference(conference);
        var diff = reservedSeats - alreadyBookedSeats.intValue();
        return (diff <=0) ? 0 : diff;
    }

    @Override
    @Transactional
    public void addParticipant(Long conferenceId, Long participantId) {
        ConferenceParticipant conferenceParticipant = new ConferenceParticipant();
        conferenceParticipant.setConference(entityManager.getReference(Conference.class, conferenceId));
        conferenceParticipant.setParticipant(entityManager.getReference(Participant.class, participantId));
        conferenceParticipantRepository.save(conferenceParticipant);
    }

    @Override
    @Transactional
    public void removeParticipant(Long conferenceId, Long participantId) {
        conferenceParticipantRepository.deleteById(new ConferenceParticipantId(conferenceId, participantId));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ParticipantDto> findAllParticipants(Long conferenceId, Pageable pageable) {
        Page<ParticipantDto> result =
                conferenceParticipantRepository.findByConference(entityManager.getReference(Conference.class, conferenceId), pageable)
                        .map(cp->participantMapper.toDto(cp.getParticipant()));
        return result;
    }

}
