package com.popov.conference_challenge.repository;

import com.popov.conference_challenge.service.domain.Conference;
import com.popov.conference_challenge.service.domain.ConferenceParticipant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConferenceParticipantRepository {
    Long countByConference(Conference conference);
    ConferenceParticipant save(ConferenceParticipant conferenceParticipant);
    void deleteById(ConferenceParticipant conferenceParticipant);
    Page<ConferenceParticipant> findByConference(Long conferenceId, Pageable pageable);
}
