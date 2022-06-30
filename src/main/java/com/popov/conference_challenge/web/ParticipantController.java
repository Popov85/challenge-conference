package com.popov.conference_challenge.web;

import com.popov.conference_challenge.service.ParticipantService;
import com.popov.conference_challenge.service.dto.ParticipantDto;
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
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping(value = "/participants", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParticipantDto> saveParticipant(@Valid @RequestBody ParticipantDto dto) {
        ParticipantDto participantDto = participantService.saveParticipant(dto);
        log.debug("Saved a participant = {}", participantDto);
        return ResponseEntity.ok(participantDto);
    }

    @PatchMapping(value = "/participants/{participantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParticipantDto> deactivateParticipant(@PathVariable Long participantId) {
        ParticipantDto participantDto = participantService.deactivateParticipant(participantId);
        log.debug("Deactivated a participant = {}", participantDto);
        return ResponseEntity.ok(participantDto);
    }

}
