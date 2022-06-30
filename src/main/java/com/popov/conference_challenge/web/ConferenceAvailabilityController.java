package com.popov.conference_challenge.web;

import com.popov.conference_challenge.service.ConferenceParticipantService;
import com.popov.conference_challenge.service.dto.AvailabilityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
public class ConferenceAvailabilityController {

    private final ConferenceParticipantService conferenceParticipantService;

    public ConferenceAvailabilityController(ConferenceParticipantService conferenceParticipantService) {
        this.conferenceParticipantService = conferenceParticipantService;
    }

    @GetMapping(value = "/conference-availability/{conferenceId}")
    public ResponseEntity<AvailabilityDto> checkAvailability(@PathVariable Long conferenceId) {
        log.debug("Requested check of availability for conferenceId = {}", conferenceId);
        return ResponseEntity.ok(new AvailabilityDto(conferenceParticipantService.checkAvailability(conferenceId)));
    }

}
