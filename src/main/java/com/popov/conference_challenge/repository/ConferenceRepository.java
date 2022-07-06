package com.popov.conference_challenge.repository;

import com.popov.conference_challenge.service.domain.Conference;

public interface ConferenceRepository {
    Conference findById(Long id);
    Conference save(Conference conference);
}
