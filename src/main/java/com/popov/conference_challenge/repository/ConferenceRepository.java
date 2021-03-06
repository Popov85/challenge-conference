package com.popov.conference_challenge.repository;

import com.popov.conference_challenge.repository.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
