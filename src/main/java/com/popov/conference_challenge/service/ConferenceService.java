package com.popov.conference_challenge.service;

import com.popov.conference_challenge.service.dto.ConferenceDto;

public interface ConferenceService {
    ConferenceDto saveConference(ConferenceDto conference);
    ConferenceDto cancelConference(Long conferenceId);
}
