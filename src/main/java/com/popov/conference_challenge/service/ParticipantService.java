package com.popov.conference_challenge.service;

import com.popov.conference_challenge.service.dto.ParticipantDto;

public interface ParticipantService {
    ParticipantDto saveParticipant(ParticipantDto participant);
    ParticipantDto deactivateParticipant(Long participantId);
}
