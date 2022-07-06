package com.popov.conference_challenge.repository;

import com.popov.conference_challenge.service.domain.Participant;

public interface ParticipantRepository {
    Participant findById(Long id);
    Participant save(Participant participant);
}
