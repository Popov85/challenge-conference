package com.popov.conference_challenge.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="conference_participant")
public class ConferenceParticipant {

    @EmbeddedId
    private ConferenceParticipantId conferenceParticipantId = new ConferenceParticipantId();

    @MapsId("conferenceId")
    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;

    @MapsId("participantId")
    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;
}
