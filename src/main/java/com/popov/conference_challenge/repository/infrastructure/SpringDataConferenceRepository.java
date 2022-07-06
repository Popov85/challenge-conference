package com.popov.conference_challenge.repository.infrastructure;

import com.popov.conference_challenge.repository.entity.ConferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataConferenceRepository extends JpaRepository<ConferenceEntity, Long> {
}
