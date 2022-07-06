package com.popov.conference_challenge.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="conference_participant")
@AllArgsConstructor
@NoArgsConstructor
public class ConferenceParticipantEntity {

    @EmbeddedId
    private ConferenceParticipantEntityId conferenceParticipantId = new ConferenceParticipantEntityId();

    @MapsId("conferenceId")
    @ManyToOne
    @JoinColumn(name = "conference_id")
    private ConferenceEntity conference;

    @MapsId("participantId")
    @ManyToOne
    @JoinColumn(name = "participant_id")
    private ParticipantEntity participant;
}
