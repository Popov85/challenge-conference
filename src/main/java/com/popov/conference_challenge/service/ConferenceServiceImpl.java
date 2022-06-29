package com.popov.conference_challenge.service;

import com.popov.conference_challenge.repository.ConferenceRepository;
import com.popov.conference_challenge.repository.entity.Conference;
import com.popov.conference_challenge.service.dto.ConferenceDto;
import com.popov.conference_challenge.service.mapper.ConferenceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
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
        Conference result = conferenceRepository.save(conferenceMapper.toEntity(dto));
        return conferenceMapper.toDto(result);
    }

    @Override
    @Transactional
    public ConferenceDto cancelConference(Long conferenceId) {
        Conference conference =
                conferenceRepository.findById(conferenceId)
                        .orElseThrow(()->new RuntimeException("Entity not found, conferenceId = "+conferenceId));
        conference.setCancelled(true);
        return new ConferenceDto();
    }

}
