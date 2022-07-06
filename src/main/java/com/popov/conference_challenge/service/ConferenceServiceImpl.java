package com.popov.conference_challenge.service;

import com.popov.conference_challenge.repository.ConferenceRepository;
import com.popov.conference_challenge.service.domain.Conference;
import com.popov.conference_challenge.service.dto.ConferenceDto;
import com.popov.conference_challenge.service.mapper.ConferenceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository;

    private final ConferenceMapper conferenceMapper;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository, ConferenceMapper conferenceMapper) {
        this.conferenceRepository = conferenceRepository;
        this.conferenceMapper = conferenceMapper;
    }

    @Override
    @Transactional
    public ConferenceDto saveConference(ConferenceDto dto) {
        Conference result = conferenceRepository.save(conferenceMapper.toDomain(dto));
        return conferenceMapper.toDto(result);
    }

    @Override
    @Transactional
    public ConferenceDto cancelConference(Long conferenceId) {
        Conference conference =
                conferenceRepository.findById(conferenceId);
        conference.cancel();
        return conferenceMapper.toDto(conferenceRepository.save(conference));
    }

}
