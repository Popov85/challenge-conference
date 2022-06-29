package com.popov.conference_challenge.repository;

import com.popov.conference_challenge.repository.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
