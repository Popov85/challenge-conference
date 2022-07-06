package com.popov.conference_challenge.repository.infrastructure;

import com.popov.conference_challenge.repository.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
}
