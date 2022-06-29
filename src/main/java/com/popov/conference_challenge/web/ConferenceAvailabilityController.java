package com.popov.conference_challenge.web;

import com.popov.conference_challenge.service.ConferenceParticipantService;
import com.popov.conference_challenge.service.dto.ParticipantDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class ConferenceAvailabilityController {

    private final ConferenceParticipantService conferenceParticipantService;

    public ConferenceAvailabilityController(ConferenceParticipantService conferenceParticipantService) {
        this.conferenceParticipantService = conferenceParticipantService;
    }


    @GetMapping(value = "/conference-availability/{conferenceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> checkAvailability(@PathVariable Long conferenceId) {
        log.debug("Requested check of availability for conferenceId = {}", conferenceId);
        return ResponseEntity.ok(conferenceParticipantService.checkAvailability(conferenceId));
    }

}
