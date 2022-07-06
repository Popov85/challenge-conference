package com.popov.conference_challenge.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceParticipant {
    private Long conferenceId;
    private Long participantId;
}
