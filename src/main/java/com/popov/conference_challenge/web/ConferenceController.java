package com.popov.conference_challenge.web;

import com.popov.conference_challenge.service.ConferenceService;
import com.popov.conference_challenge.service.dto.ConferenceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ConferenceController {

    private final ConferenceService conferenceService;

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @PostMapping(value = "/conferences", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConferenceDto> saveConference(@Valid @RequestBody ConferenceDto dto) {
        ConferenceDto conferenceDto = conferenceService.saveConference(dto);
        log.debug("Saved Conference = {}", conferenceDto);
        return ResponseEntity.ok(conferenceDto);
    }

    @PatchMapping(value = "/conferences/{conferenceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConferenceDto> cancelConference(@PathVariable Long conferenceId) {
        ConferenceDto conferenceDto = conferenceService.cancelConference(conferenceId);
        log.debug("Cancelled Conference, conference = {}", conferenceDto);
        return ResponseEntity.ok(conferenceDto);
    }

}
