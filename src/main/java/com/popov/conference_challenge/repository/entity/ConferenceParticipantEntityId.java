package com.popov.conference_challenge.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceParticipantEntityId implements Serializable {
    @Column(name = "conference_id")
    private Long conferenceId;

    @Column(name = "participant_id")
    private Long participantId;
}
