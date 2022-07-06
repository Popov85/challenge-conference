package com.popov.conference_challenge.configuration;

import com.popov.conference_challenge.repository.ConferenceParticipantRepository;
import com.popov.conference_challenge.repository.ConferenceRepository;
import com.popov.conference_challenge.repository.ParticipantRepository;
import com.popov.conference_challenge.service.*;
import com.popov.conference_challenge.service.mapper.ConferenceMapper;
import com.popov.conference_challenge.service.mapper.ParticipantMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ConferenceService getConferenceService(ConferenceMapper conferenceMapper,
                                                  ConferenceRepository conferenceRepository) {
        return new ConferenceServiceImpl(conferenceRepository, conferenceMapper);
    }

    @Bean
    public ParticipantService getParticipantService(ParticipantMapper participantMapper,
                                                    ParticipantRepository participantRepository) {
        return new ParticipantServiceImpl(participantRepository, participantMapper);
    }

    @Bean
    public ConferenceParticipantService getConferenceParticipantService(ParticipantMapper participantMapper,
                                                                        ConferenceRepository conferenceRepository,
                                                                        ParticipantRepository participantRepository,
                                                                        ConferenceParticipantRepository conferenceParticipantRepository) {
        return new ConferenceParticipantServiceImpl(participantMapper, conferenceRepository, participantRepository, conferenceParticipantRepository);
    }
}
