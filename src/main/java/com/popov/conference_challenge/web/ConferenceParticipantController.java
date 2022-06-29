package com.popov.conference_challenge.web;

import com.popov.conference_challenge.service.ConferenceParticipantService;
import com.popov.conference_challenge.service.dto.ParticipantDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class ConferenceParticipantController {

    private final ConferenceParticipantService conferenceParticipantService;

    public ConferenceParticipantController(ConferenceParticipantService conferenceParticipantService) {
        this.conferenceParticipantService = conferenceParticipantService;
    }

    @PostMapping(value = "/conferences/{conferenceId}/participants/{participantId}/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addParticipant(@PathVariable Long conferenceId, @PathVariable Long participantId) {
        log.debug("Adding a participant = {} to conference = {}", participantId, conferenceId);
        conferenceParticipantService.addParticipant(conferenceId, participantId);
    }

    @DeleteMapping(value = "/conferences/{conferenceId}/participants/{participantId}/remove")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeParticipant(@PathVariable Long conferenceId, @PathVariable Long participantId) {
        log.debug("Removing a participant = {} to conference = {}", participantId, conferenceId);
        conferenceParticipantService.removeParticipant(conferenceId, participantId);
    }

    @GetMapping(value = "/conferences/{conferenceId}/participants", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ParticipantDto> findAllParticipants(@PathVariable Long conferenceId, @PageableDefault(sort = {"participant.username"}, value = 15) Pageable pageable) {
        log.debug("Requested all participants for conference with id = {}", conferenceId);
        return conferenceParticipantService.findAllParticipants(conferenceId, pageable);
    }

}
