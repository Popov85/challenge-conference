package com.popov.conference_challenge.repository.infrastructure;

import com.popov.conference_challenge.repository.entity.ConferenceEntity;
import com.popov.conference_challenge.repository.entity.ConferenceParticipantEntity;
import com.popov.conference_challenge.repository.entity.ConferenceParticipantEntityId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataConferenceParticipantRepository extends JpaRepository<ConferenceParticipantEntity, ConferenceParticipantEntityId> {
    Long countByConference(ConferenceEntity conference);

    Page<ConferenceParticipantEntity> findByConference(ConferenceEntity conference, Pageable pageable);
}
