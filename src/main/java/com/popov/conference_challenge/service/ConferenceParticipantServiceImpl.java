package com.popov.conference_challenge.service;

import com.popov.conference_challenge.repository.ConferenceParticipantRepository;
import com.popov.conference_challenge.repository.ConferenceRepository;
import com.popov.conference_challenge.repository.ParticipantRepository;
import com.popov.conference_challenge.service.domain.Conference;
import com.popov.conference_challenge.service.domain.ConferenceParticipant;
import com.popov.conference_challenge.service.dto.ParticipantDto;
import com.popov.conference_challenge.service.mapper.ParticipantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ConferenceParticipantServiceImpl implements ConferenceParticipantService {

     private final ParticipantMapper participantMapper;

    private final ConferenceRepository conferenceRepository;

    private final ParticipantRepository participantRepository;

    private final ConferenceParticipantRepository conferenceParticipantRepository;

    public ConferenceParticipantServiceImpl(ParticipantMapper participantMapper,
                                            ConferenceRepository conferenceRepository,
                                            ParticipantRepository participantRepository,
                                            ConferenceParticipantRepository conferenceParticipantRepository) {
        this.participantMapper = participantMapper;
        this.conferenceRepository = conferenceRepository;
        this.participantRepository = participantRepository;
        this.conferenceParticipantRepository = conferenceParticipantRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer checkAvailability(Long conferenceId) {
        Conference conference = conferenceRepository.findById(conferenceId);
        var reservedSeats = conference.getSeats();
        var alreadyBookedSeats = conferenceParticipantRepository.countByConference(conference);
        var diff = reservedSeats - alreadyBookedSeats.intValue();
        return (diff <=0) ? 0 : diff;
    }

    @Override
    @Transactional
    public void addParticipant(Long conferenceId, Long participantId) {
        // Are there any free seats?
        var availableSeats = checkAvailability(conferenceId);
        if (availableSeats==0)
            throw new RuntimeException("All the seats have been already booked!");
        ConferenceParticipant conferenceParticipant = new ConferenceParticipant();
        conferenceParticipant.setConferenceId(conferenceId);
        conferenceParticipant.setParticipantId(participantId);
        conferenceParticipantRepository.save(conferenceParticipant);
    }

    @Override
    @Transactional
    public void removeParticipant(Long conferenceId, Long participantId) {
        conferenceParticipantRepository.deleteById(new ConferenceParticipant(conferenceId, participantId));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ParticipantDto> findAllParticipants(Long conferenceId, Pageable pageable) {
        Page<ParticipantDto> result = conferenceParticipantRepository.findByConference(conferenceId, pageable)
                .map(cp->participantMapper.toDto(participantRepository.findById(cp.getParticipantId())));
        return result;
    }

}
