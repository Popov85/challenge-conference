package com.popov.conference_challenge.repository;

import com.popov.conference_challenge.repository.entity.Conference;
import com.popov.conference_challenge.repository.entity.ConferenceParticipant;
import com.popov.conference_challenge.repository.entity.ConferenceParticipantId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceParticipantRepository extends JpaRepository<ConferenceParticipant, ConferenceParticipantId> {
    Long countByConference(Conference conference);

    Page<ConferenceParticipant> findByConference(Conference conference, Pageable pageable);
}
