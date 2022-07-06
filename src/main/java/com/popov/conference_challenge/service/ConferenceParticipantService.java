package com.popov.conference_challenge.service;

import com.popov.conference_challenge.service.dto.ParticipantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConferenceParticipantService {
    Integer checkAvailability(Long conferenceId);
    void addParticipant(Long conferenceId, Long participantId);
    void removeParticipant(Long conferenceId, Long participantId);
    Page<ParticipantDto> findAllParticipants(Long conferenceId, Pageable pageable);
}
